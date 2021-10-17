

package org.datasphere.mdm.data.dao;

import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.datasphere.mdm.data.dao.impl.BaseStorageDAOImpl.DataNodeEntry;
import org.datasphere.mdm.data.po.storage.DataClusterPO;
import org.datasphere.mdm.core.type.keys.ExternalId;
import org.datasphere.mdm.system.dao.BaseDAO;

/**
 * @author Mikhail Mikhailov
 * Data storage DAO base class.
 */
public interface BaseStorageDAO extends BaseDAO {
    /**
     * Runs DAO storage configuration for the given cluster state.
     * @param po the cluster state
     */
    void configure(DataClusterPO po);
    /**
     * Storage for data nodes.
     * @return list of nodes
     */
    @Nonnull
    List<DataNodeEntry> nodes();
    /**
     * Selects connection entry by node number directly.
     * @param node the node number
     * @return connection entry
     */
    @Nullable
    DataNodeEntry nodeSelect(int node);
    /**
     * Selects connection entry by shard number.
     * @param shard the shard number
     * @return connection entry
     */
    @Nullable
    DataNodeEntry shardSelect(int shard);
    /**
     * Selects default connection entry.
     * @return connection entry
     */
    @Nullable
    DataNodeEntry defaultSelect();
    /**
     * Selects connection entry by etalon id.
     * @param uuid the record (record/clsf/rel) UUID id
     * @return connection entry
     */
    @Nullable
    DataNodeEntry keySelect(String uuid);
    /**
     * Selects connection entry by etalon id.
     * @param uuid the record (record/clsf/rel) UUID id
     * @return connection entry
     */
    @Nullable
    DataNodeEntry keySelect(UUID uuid);
    /**
     * Selects connection entry by record external id.
     * @param id ext id
     * @param name entity name
     * @param system source system
     * @return connection entry
     */
    @Nullable
    DataNodeEntry keySelect(String id, String name, String system);
    /**
     * Selects connection entry by record external id.
     * @param id ext id
     * @return connection entry
     */
    @Nullable
    DataNodeEntry keySelect(ExternalId id);
    /**
     * Stops and cleans the underlaying DSs.
     */
    void shutdown();
}
