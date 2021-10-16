

package org.datasphere.mdm.core.type.data;

/**
 * @author Mikhail Mikhailov
 * Approval state.
 */
public enum ApprovalState {
    /**
     * Denotes state of pending changes for a version.
     */
    PENDING,
    /**
     * Denotes approved state of a version.
     */
    APPROVED,
    /**
     * Denotes declined state of a version.
     */
    DECLINED;
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
    public static ApprovalState fromValue(String v) {
        return valueOf(v);
    }

}
