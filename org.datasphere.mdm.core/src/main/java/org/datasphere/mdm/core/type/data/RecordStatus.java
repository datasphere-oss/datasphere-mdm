

package org.datasphere.mdm.core.type.data;

/**
 * @author Mikhail Mikhailov
 * Record status.
 */
public enum RecordStatus {
    /**
     * The record is active.
     */
    ACTIVE,
    /**
     * The record is inactive.
     */
    INACTIVE,
    /**
     * The record is inactive merged.
     */
    MERGED;
    /**
     * Convenient value method.
     * @return value / name.
     */
    public String value() {
        return name();
    }
    /**
     * Covenient from value wmethod.
     * @param v value / name
     * @return parsed value
     */
    public static RecordStatus fromValue(String v) {
        return valueOf(v);
    }
}
