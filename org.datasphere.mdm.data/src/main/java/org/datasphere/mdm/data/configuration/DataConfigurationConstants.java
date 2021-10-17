
package org.datasphere.mdm.data.configuration;

import org.datasphere.mdm.data.module.DataModule;

/**
 * Various constants.
 * @author Mikhail Mikhailov on Nov 12, 2019
 */
public final class DataConfigurationConstants {
	/**
     * Core data source properties prefix.
     */
    public static final String DATA_DATASOURCE_PROPERTIES_PREFIX = DataModule.MODULE_ID + ".datasource.";
    /**
     * Data storage schema name.
     */
    public static final String DATA_STORAGE_SCHEMA_NAME = "org_unidata_mdm_data";
    /**
     * Meta tables change log name.
     */
    public static final String META_LOG_NAME = "meta_change_log";
    /**
     * Data tables change log name.
     */
    public static final String DATA_LOG_NAME = "data_change_log";
    /**
     * Storage init lock name.
     */
    public static final String STORAGE_LOCK_NAME = "DATA_STORAGE_LOCK";

    // Config properties
    /**
     * Data group.
     */
    public static final String PROPERTY_DATA_GROUP = DataModule.MODULE_ID + ".data.group";
    /**
     * Boostrap nodes description.
     */
    public static final String PROPERTY_DATA_NODES = DataModule.MODULE_ID + ".nodes";
    /**
     * Boostrap shards description.
     */
    public static final String PROPERTY_DATA_SHARDS = DataModule.MODULE_ID + ".shards";
    /**
     * Index group.
     */
    public static final String PROPERTY_INDEX_GROUP = DataModule.MODULE_ID + ".index.group";
    /**
     * Index only one side of relation.
     * Former unidata.search.index.relations.straight
     */
    public static final String PROPERTY_INDEX_RELATIONS_STRAIGHT = DataModule.MODULE_ID + ".index.relations.straight";
    /**
     * Search date display format.
     * Former unidata.search.display.date.format
     */
    public static final String PROPERTY_INDEX_DATE_DISPLAY_FORMAT = DataModule.MODULE_ID + ".index.date.display.format";
    /**
     * Search time display format.
     * Former unidata.search.display.time.format
     */
    public static final String PROPERTY_INDEX_TIME_DISPLAY_FORMAT = DataModule.MODULE_ID + ".index.time.display.format";
    /**
     * Search timestamp display format.
     * Former unidata.search.display.timestamp.format
     */
    public static final String PROPERTY_INDEX_TIMESTAMP_DISPLAY_FORMAT = DataModule.MODULE_ID + ".index.timestamp.display.format";
    // End of config properties

    /**
     * Constructor.
     */
    private DataConfigurationConstants() {
        super();
    }
}
