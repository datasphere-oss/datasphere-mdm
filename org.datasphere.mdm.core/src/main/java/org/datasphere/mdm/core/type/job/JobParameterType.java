

package org.datasphere.mdm.core.type.job;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Denis Kostovarov
 * Supported value types.
 */
public enum JobParameterType {
    /**
     * Plain string.
     */
    STRING,
    /**
     * Same as string, but indicates potentially large volume, that should be displayed in a different control.
     */
    CLOB,
    /**
     * Boolean.
     */
    BOOLEAN,
    /**
     * 64 bit signed integer (long).
     */
    INTEGER,
    /**
     * 64 bit signed fp number (double).
     */
    NUMBER,
    /**
     * LocalDate.
     */
    DATE,
    /**
     * LocalTime.
     */
    TIME,
    /**
     * LocalDateTime.
     */
    TIMESTAMP,
    /**
     * Instant.
     */
    INSTANT,
    /**
     * A custom type, serialized to string.
     */
    CUSTOM;
    /**
     * Non-throwing, case insensetive valueOf(String).
     * @param v the string
     * @return {@linkplain JobParameterType} or null
     */
    @Nullable
    public static JobParameterType fromValue(final String v) {

        for (int i = 0; i < JobParameterType.values().length; i++) {

            final JobParameterType c = JobParameterType.values()[i];
            if (StringUtils.equalsIgnoreCase(v, c.name())) {
                return c;
            }

        }

        return null;
    }
}
