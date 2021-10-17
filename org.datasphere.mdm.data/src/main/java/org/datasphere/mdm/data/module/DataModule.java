

package org.datasphere.mdm.data.module;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.configuration.DataConfiguration;
import org.datasphere.mdm.data.configuration.DataConfigurationConstants;
import org.datasphere.mdm.data.configuration.DataConfigurationProperty;
import org.datasphere.mdm.data.configuration.DataMessagingDomain;
import org.datasphere.mdm.data.configuration.DataNamespace;
import org.datasphere.mdm.data.context.DistributedMigrationContext;
import org.datasphere.mdm.data.convert.DataClusterConverter;
import org.datasphere.mdm.data.dao.StorageDAO;
import org.datasphere.mdm.data.exception.DataExceptionIds;
import org.datasphere.mdm.data.migration.data.DataMigrations;
import org.datasphere.mdm.data.migration.meta.MetaMigrations;
import org.datasphere.mdm.data.po.storage.DataClusterPO;
import org.datasphere.mdm.data.type.storage.DataCluster;
import org.datasphere.mdm.data.util.DataDiffUtils;
import org.datasphere.mdm.data.util.RecordFactoryUtils;
import org.datasphere.mdm.data.util.StorageUtils;
import org.datasphere.mdm.system.configuration.SystemConfigurationConstants;
import org.datasphere.mdm.system.context.DatabaseMigrationContext;
import org.datasphere.mdm.system.exception.PlatformFailureException;
import org.datasphere.mdm.system.service.DatabaseMigrationService;
import org.datasphere.mdm.system.type.annotation.ConfigurationRef;
import org.datasphere.mdm.system.type.configuration.ConfigurationProperty;
import org.datasphere.mdm.system.type.configuration.ConfigurationValue;
import org.datasphere.mdm.system.type.messaging.DomainType;
import org.datasphere.mdm.system.type.module.AbstractModule;
import org.datasphere.mdm.system.type.module.Dependency;
import org.datasphere.mdm.system.type.namespace.NameSpace;
import org.datasphere.mdm.system.type.pipeline.Segment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class DataModule extends AbstractModule {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataModule.class);
    /**
     * Deps.
     */
    private static final Set<Dependency> DEPENDENCIES = Set.of(
            new Dependency("org.datasphere.mdm.meta", "6.0"),new Dependency("org.datasphere.mdm.draft", "6.0"));
    /**
     * org.datasphereis module id.
     */
    public static final String MODULE_ID = "org.datasphere.mdm.data";

    @ConfigurationRef(DataConfigurationConstants.PROPERTY_DATA_NODES)
    private ConfigurationValue<String> nodes;

    @ConfigurationRef(DataConfigurationConstants.PROPERTY_DATA_SHARDS)
    private ConfigurationValue<Long> shards;

    @Autowired
    private DataConfiguration configuration;

    @Autowired
    @Qualifier("storageDataSource")
    private DataSource storageDataSource;
    /**
     * Storage metadata DAO.
     */
    @Autowired
    private StorageDAO dataStorageDAO;

    @Autowired
    private DatabaseMigrationService migrationService;
    /**
     * Whether we run in developer mode.
     */
    @ConfigurationRef(SystemConfigurationConstants.PROPERTY_DEVELOPER_MODE)
    private ConfigurationValue<Boolean> developerMode;

    private boolean install;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return MODULE_ID;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getVersion() {
        return "6.0";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Data";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return "Unidata Data module";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Dependency> getDependencies() {
        return DEPENDENCIES;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String[] getResourceBundleBasenames() {
        return new String[]{ "data_messages" };
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigurationProperty<?>[] getConfigurationProperties() {
        return DataConfigurationProperty.values();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DomainType[] getMessagingDomains() {
        return new DomainType[] { DataMessagingDomain.DOMAIN };
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public NameSpace[] getNameSpaces() {
        return DataNamespace.values();
    }
    /**
     * Initialization logic is quite complicated:
     * - schema may not exist
     * - cluster metadata may not exist
     * {@inheritDoc}
     */
    @Override
    public void install() {

        LOGGER.info("Install");

        // Install/Migrate storage schemas.
        migrate();
        install = true;
    }

    @Override
    public void uninstall() {
        LOGGER.info("Uninstall");
        // TODO: UN-11830 Uninstall schema
    }

    @Override
    public void start() {

        LOGGER.info("Starting...");

        // 1. Static utils
        StorageUtils.init();
        RecordFactoryUtils.init();
        DataDiffUtils.init();

        if (developerMode.getValue() && !install) {
            migrate();
        }

        // 2. Add segments
        addSegments(configuration.getBeansOfType(Segment.class).values());

        LOGGER.info("Started.");
    }

    @Override
    public void stop() {

        LOGGER.info("Stopping...");
        dataStorageDAO.shutdown();
        LOGGER.info("Stopped.");
    }

    private void migrate() {

        // 1. Storage metadata
        migrationService.migrate(DatabaseMigrationContext.builder()
                .schemaName(DataConfigurationConstants.DATA_STORAGE_SCHEMA_NAME)
                .logName(DataConfigurationConstants.META_LOG_NAME)
                .dataSource(storageDataSource)
                .migrations(MetaMigrations.migrations())
                .build());

        // 2. Load-or-default cluster info. Stuff is temporary.
        DataClusterPO clusterPO = dataStorageDAO.load();
        if (clusterPO == null) {

            if (!shards.hasValue() || !nodes.hasValue()) {
                throw new PlatformFailureException(
                        "Data storage configuration not found in both DB and system properties.",
                        DataExceptionIds.EX_DATA_STORAGE_NOT_CONFIGURED);
            }

            clusterPO = DataClusterConverter.of(shards.getValue().intValue(), StringUtils.split(nodes.getValue(), ','));
            clusterPO.setVersion(1); // Avoid reload of spec on the very first run

            dataStorageDAO.save(clusterPO);
        }

        dataStorageDAO.configure(clusterPO);

        // 3. Install/Migrate data schema.
        DataCluster tempCluster = DataClusterConverter.of(clusterPO);
        List<DatabaseMigrationContext> migrations = Arrays.stream(tempCluster.getNodes())
                .map(node -> DistributedMigrationContext.distributed()
                        .cluster(tempCluster)
                        .node(node)
                        .schemaName(DataConfigurationConstants.DATA_STORAGE_SCHEMA_NAME)
                        .logName(DataConfigurationConstants.DATA_LOG_NAME)
                        .migrations(DataMigrations.migrations())
                        .dataSource(dataStorageDAO.nodeSelect(node.getNumber()).dataSource())
                        .build())
                .collect(Collectors.toList());

        migrationService.migrate(migrations);
    }
}
