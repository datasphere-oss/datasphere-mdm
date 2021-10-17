package org.datasphere.mdm.data.configuration;

import java.time.format.DateTimeFormatter;

import org.datasphere.mdm.data.module.DataModule;
import org.datasphere.mdm.system.type.configuration.ConfigurationProperty;

/**
 * Data properties
 * @author Mikhail Mikhailov on Apr 20, 2020
 */
public final class DataConfigurationProperty {
    /**
     * No-instance constructor.
     */
    private DataConfigurationProperty() {
        super();
    }
    /**
     * Data nodes.
     */
    public static final ConfigurationProperty<String> DATA_NODES = ConfigurationProperty.string()
            .key(DataConfigurationConstants.PROPERTY_DATA_NODES)
            .groupKey(DataConfigurationConstants.PROPERTY_DATA_GROUP)
            .moduleId(DataModule.MODULE_ID)
            .required(true)
            .readOnly(true)
            .build();
    /**
     * Data shards.
     */
    public static final ConfigurationProperty<Long> DATA_SHARDS = ConfigurationProperty.integer()
            .key(DataConfigurationConstants.PROPERTY_DATA_SHARDS)
            .groupKey(DataConfigurationConstants.PROPERTY_DATA_GROUP)
            .moduleId(DataModule.MODULE_ID)
            .defaultValue(Long.valueOf(1L))
            .required(true)
            .readOnly(true)
            .build();
    /**
     * FIXME: Use it.
     * Index one side only.
     */
    public static final ConfigurationProperty<Boolean> DATA_INDEX_RELATIONS_STRAIGHT = ConfigurationProperty.bool()
            .key(DataConfigurationConstants.PROPERTY_INDEX_RELATIONS_STRAIGHT)
            .groupKey(DataConfigurationConstants.PROPERTY_INDEX_GROUP)
            .moduleId(DataModule.MODULE_ID)
            .defaultValue(Boolean.FALSE)
            .required(false)
            .readOnly(true)
            .build();
    /**
     * Index one side only.
     */
    public static final ConfigurationProperty<DateTimeFormatter> DATA_INDEX_DATE_DISPLAY_FORMAT = ConfigurationProperty.custom(DateTimeFormatter.class)
            .key(DataConfigurationConstants.PROPERTY_INDEX_DATE_DISPLAY_FORMAT)
            .groupKey(DataConfigurationConstants.PROPERTY_INDEX_GROUP)
            .moduleId(DataModule.MODULE_ID)
            .deserializer(DateTimeFormatter::ofPattern)
            .defaultValue(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            .required(false)
            .readOnly(true)
            .build();
    /**
     * Index one side only.
     */
    public static final ConfigurationProperty<DateTimeFormatter> DATA_INDEX_TIME_DISPLAY_FORMAT = ConfigurationProperty.custom(DateTimeFormatter.class)
            .key(DataConfigurationConstants.PROPERTY_INDEX_TIME_DISPLAY_FORMAT)
            .groupKey(DataConfigurationConstants.PROPERTY_INDEX_GROUP)
            .moduleId(DataModule.MODULE_ID)
            .deserializer(DateTimeFormatter::ofPattern)
            .defaultValue(DateTimeFormatter.ofPattern("HH:mm:ss"))
            .required(false)
            .readOnly(true)
            .build();
    /**
     * Index one side only.
     */
    public static final ConfigurationProperty<DateTimeFormatter> DATA_INDEX_TIMESTAMP_DISPLAY_FORMAT = ConfigurationProperty.custom(DateTimeFormatter.class)
            .key(DataConfigurationConstants.PROPERTY_INDEX_TIMESTAMP_DISPLAY_FORMAT)
            .groupKey(DataConfigurationConstants.PROPERTY_INDEX_GROUP)
            .moduleId(DataModule.MODULE_ID)
            .deserializer(DateTimeFormatter::ofPattern)
            .defaultValue(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
            .required(false)
            .readOnly(true)
            .build();
    /**
     * Values as array.
     */
    private static final ConfigurationProperty<?>[] VALUES = {
            DATA_INDEX_DATE_DISPLAY_FORMAT,
            DATA_INDEX_RELATIONS_STRAIGHT,
            DATA_INDEX_TIME_DISPLAY_FORMAT,
            DATA_INDEX_TIMESTAMP_DISPLAY_FORMAT,
            DATA_NODES,
            DATA_SHARDS
    };
    /**
     * Enum like array accessor.
     * @return array of values.
     */
    public static ConfigurationProperty<?>[] values() {
        return VALUES;
    }
}
