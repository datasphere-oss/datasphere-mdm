
package org.datasphere.mdm.core.type.data;

import java.util.Date;

/**
 * @author Mikhail Mikhailov on Sep 14, 2021
 * A data record, bounded by validity period.
 * The dates are taken from info section in case of records,
 * but this may be implemented differently.
 */
public interface DataFrame extends DataRecord {
    /**
     * Gets the starting point of validity period.
     * @return the starting point of validity period
     */
    Date getValidFrom();
    /**
     * Gets the ending point of validity period.
     * @return the ending point of validity period
     */
    Date getValidTo();
}
