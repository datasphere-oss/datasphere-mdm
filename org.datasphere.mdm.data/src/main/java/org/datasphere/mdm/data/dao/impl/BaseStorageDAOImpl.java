

package org.datasphere.mdm.data.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.sql.DataSource;

import org.datasphere.mdm.data.configuration.DataConfigurationConstants;
import org.datasphere.mdm.data.dao.BaseStorageDAO;
import org.datasphere.mdm.data.dao.cluster.ClusterUtils;
import org.datasphere.mdm.data.exception.DataExceptionIds;
import org.datasphere.mdm.data.po.storage.DataClusterPO;
import org.datasphere.mdm.data.po.storage.DataNodePO;
import org.datasphere.mdm.data.util.StorageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.system.configuration.SystemConfigurationConstants;
import org.datasphere.mdm.system.dao.impl.BaseDAOImpl;
import org.datasphere.mdm.system.exception.PlatformFailureException;
import org.datasphere.mdm.system.service.IdentityService;
import org.datasphere.mdm.system.type.annotation.ConfigurationRef;
import org.datasphere.mdm.system.type.configuration.ConfigurationValue;
import org.datasphere.mdm.system.type.format.DumpTargetFormat;
import org.datasphere.mdm.system.util.DataSourceUtils;

/**
 * @author Mikhail Mikhailov
 * Base DAO for data storage operations.
 */
public abstract class BaseStorageDAOImpl extends BaseDAOImpl implements BaseStorageDAO {
    /**
     * Nodes.
     */
    protected static CopyOnWriteArrayList<DataNodeEntry> nodes = new CopyOnWriteArrayList<>();
    /**
     * Init hooks.
     */
    protected static CopyOnWriteArrayList<Consumer<DataClusterPO>> hooks = new CopyOnWriteArrayList<>();
    /**
     * This logger
     */
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseStorageDAOImpl.class);
    /**
     * The default dump target format.
     */
    @ConfigurationRef(SystemConfigurationConstants.PROPERTY_DUMP_TARGET_FORMAT)
    protected ConfigurationValue<DumpTargetFormat> dumpTargetFormat;
    /**
     * Out PC.
     */
    @Autowired
    protected IdentityService platformConfiguration;
    /**
     * The last initialized version of the CM.
     */
    protected int lastInitialized;
    /**
     * Constructor with default meta data data source.
     */
    protected BaseStorageDAOImpl(DataSource dataSource) {
        super(dataSource);
    }
    /**
     * Re-initializes the cluster storage and runs init hooks.
     * @param cluster the cluster PO.
     */
    @Override
    public void configure(DataClusterPO cluster) {

        if (cluster.getVersion() <= lastInitialized) {
            return;
        }

        nodes.forEach(DataNodeEntry::shutdown);
        nodes.clear();

        ArrayList<DataNodeEntry> tmp = new ArrayList<>();
        for (DataNodePO node : cluster.getNodes()) {

            final String id = "UD-DATA-" + platformConfiguration.getNodeId() + node.getId();
            DataSource ds = ClusterUtils.newPoolingXADataSource(node, DataConfigurationConstants.DATA_STORAGE_SCHEMA_NAME, id);
            tmp.add(new DataNodeEntry(ds));
        }

        nodes.addAll(tmp);
        hooks.forEach(c -> c.accept(cluster));
        lastInitialized = cluster.getVersion();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void shutdown() {

        List<DataNodeEntry> n = nodes();
        n.forEach(DataNodeEntry::shutdown);
        n.clear();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<DataNodeEntry> nodes() {
        return nodes;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataNodeEntry nodeSelect(int node) {
        return selectNodeByNodeNumber(node);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataNodeEntry shardSelect(int shard) {
        return selectNodeByShardNumber(shard);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataNodeEntry defaultSelect() {
        return selectNodeByNodeNumber(0);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataNodeEntry keySelect(String uuid) {
        return keySelect(UUID.fromString(uuid));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataNodeEntry keySelect(UUID uuid) {
        return shardSelect(StorageUtils.shard(uuid));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataNodeEntry keySelect(String id, String name, String system) {
        return keySelect(ExternalId.of(id, name, system));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataNodeEntry keySelect(ExternalId id) {
        return shardSelect(StorageUtils.shard(id));
    }
    /**
     * Makes an init hook function to be registered.
     * @param hook the hook to register.
     */
    protected void hook(Consumer<DataClusterPO> hook) {
        hooks.add(hook);
    }
    /**
     * Check the node number validity and returns node entry.
     * @param node the node number
     * @return {@link DataNodeEntry}
     */
    protected DataNodeEntry selectNodeByNodeNumber(int node) {

        List<DataNodeEntry> n = nodes();
        int sz = n.size();
        if (sz > 0) {

            if (node >= sz) {
                throwInvalidNodeException(node, sz);
            }

            return n.get(node);
        } else {
            throwZeroNodesInitializedException();
        }

        // Unreachable
        return null;
    }
    /**
     * Selects node by shard number.
     * @param shard the shard number
     * @return {@link DataNodeEntry}
     */
    protected DataNodeEntry selectNodeByShardNumber(int shard) {

        List<DataNodeEntry> n = nodes();
        int sz = n.size();
        if (sz > 0) {

            int node = StorageUtils.node(shard);
            if (node >= sz) {
                throwInvalidShardException(shard, node, sz);
            }

            return n.get(node);
        } else {
            throwZeroNodesInitializedException();
        }

        // Unreachable
        return null;
    }
    /**
     * Complains about invalid number shard numbers.
     * @param shard the shard
     * @param other the argument
     */
    protected void throwShardNumbersDoesNotMatchException(int shard, int other) {
        final String message = "Shard [{}] in data set does not match to the one [{}] supplied by arguments.";
        throw new PlatformFailureException(message, DataExceptionIds.EX_DATA_STORAGE_SHARD_NUMBERS_DO_NOT_MATCH, shard, other);
    }
    /**
     * Complains about invalid number of nodes.
     * @param shard the shard
     * @param node the calculated node
     * @param nodes current number of nodes
     */
    protected void throwInvalidShardException(int shard, int node, int nodes) {
        final String message = "Shard [{}] to node calculated invalid result [{}]. Current number of known nodes [{}].";
        throw new PlatformFailureException(message, DataExceptionIds.EX_DATA_STORAGE_INVALID_SHARD_REQUESTED, shard, node, nodes);
    }
    /**
     * Complains about invalid node request.
     * @param node the calculated node
     * @param nodes current number of nodes
     */
    protected void throwInvalidNodeException(int node, int nodes) {
        final String message = "Invalid node [{}] requested. Current number of known nodes [{}].";
        throw new PlatformFailureException(message, DataExceptionIds.EX_DATA_STORAGE_INVALID_NODE_REQUESTED, node, nodes);
    }
    /**
     * Complains about invalid number of nodes.
     */
    protected void throwZeroNodesInitializedException() {
        final String message = "Data storage not functioning. Zero nodes initialized by the system.";
        throw new PlatformFailureException(message, DataExceptionIds.EX_DATA_STORAGE_ZERO_NODES_INITIALIZED);
    }
    /**
     * @author Mikhail Mikhailov
     * Primitive holder entry class.
     */
    public static class DataNodeEntry {
        /**
         * The data source.
         */
        private final DataSource dataSource;
        /**
         * Jdbc template.
         */
        private final JdbcTemplate jdbcTemplate;
        /**
         * Named jdbc template.
         */
        private final NamedParameterJdbcTemplate namedJdbcTemplate;
        /**
         * Constructor.
         * @param ds the data source
         * @param jdbcTemplate jdbc template
         * @param namedJdbcTemplate named jdbc template
         */
        public DataNodeEntry(@Nonnull DataSource ds) {
            super();
            Objects.requireNonNull(ds, "Supplied data source must not be null!");
            this.dataSource = ds;
            this.jdbcTemplate = new JdbcTemplate(ds);
            this.namedJdbcTemplate = new NamedParameterJdbcTemplate(ds);
        }

        public DataSource dataSource() {
            return this.dataSource;
        }

        public JdbcTemplate jdbcTemplate() {
            return this.jdbcTemplate;
        }

        public NamedParameterJdbcTemplate namedJdbcTemplate() {
            return this.namedJdbcTemplate;
        }

        public Connection bareConnection() throws SQLException {
            return dataSource.getConnection();
        }

        public void shutdown() {
            DataSourceUtils.shutdown(this.dataSource);
        }
    }
}
