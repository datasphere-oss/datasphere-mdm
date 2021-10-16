

package org.datasphere.mdm.core.type.data;

/**
 * @author Mikhail Mikhailov
 * Data shift. 
 * Versions, saved prior to any modifications which may be carried out by DQ, UE, etc. are marked PRISTINE.
 * Versions, saved after some modifications done by DQ, UE, etc. are marked REVISED.
 */
public enum DataShift {
    /**
     * Versions, saved prior to any modifications which may be carried out by DQ, UE, etc. are marked PRISTINE.
     */
    PRISTINE,
    /**
     * Versions, saved after some modifications done by DQ, UE, etc. are marked REVISED.
     */
    REVISED;
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
    public static DataShift fromValue(String v) {
        return valueOf(v);
    }
}
