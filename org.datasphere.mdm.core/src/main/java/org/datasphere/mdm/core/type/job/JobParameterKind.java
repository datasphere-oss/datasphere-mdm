
package org.datasphere.mdm.core.type.job;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Mikhail Mikhailov on Jun 25, 2021
 * Parameter kind (layout type).
 */
public enum JobParameterKind {
    /**
     * Single value.
     */
    SINGLE,
    /**
     * Collection.
     */
    COLLECTION,
    /**
     * Map.
     */
    MAP,
    /**
     * Custom, user defined type.
     */
    CUSTOM;
    /**
     * Non-throwing, case insensetive valueOf(String).
     * @param v the string
     * @return {@linkplain JobParameterKind} or null
     */
    @Nullable
    public static JobParameterKind fromValue(final String v) {

        for (int i = 0; i < JobParameterKind.values().length; i++) {

            final JobParameterKind c = JobParameterKind.values()[i];
            if (StringUtils.equalsIgnoreCase(v, c.name())) {
                return c;
            }

        }

        return null;
    }
}
