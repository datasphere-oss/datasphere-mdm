

/**
 *
 */
package org.datasphere.mdm.core.type.data;

import java.util.Date;

/**
 * @author Mikhail Mikhailov
 * The validity range.
 */
public interface ValidityRange {
    /**
     * From date. May be null.
     * @return Date or null
     */
    Date getValidFrom();
    /**
     * To date. May be null.
     * @return Date or null
     */
    Date getValidTo();
    /**
     * Returns true, if the range is of the same 'from' value as the 'other'.
     * @param other the 'from; boundary
     * @return true, if the range is of the same 'from' value as the 'other'.
     */
    default boolean isSameFrom(Date other) {
        return getValidFrom() == null
                ? other == null
                : getValidFrom().equals(other);
    }
    /**
     * Returns true, if the range is of the same 'to' value as the 'other'.
     * @param other the 'to'  boundary
     * @return true, if the range is of the same 'to' value as the 'other'.
     */
    default boolean isSameTo(Date other) {
        return getValidTo() == null
                ? other == null
                : getValidTo().equals(other);
    }
    /**
     * Returns true for the same dates boundaries.
     * @param from the from
     * @param to the to
     * @return true for the same dates boundaries.
     */
    default boolean isSameRange(Date from, Date to) {
        return isSameFrom(from) && isSameTo(to);
    }
}
