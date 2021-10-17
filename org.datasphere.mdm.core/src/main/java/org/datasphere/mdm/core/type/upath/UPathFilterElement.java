
package org.datasphere.mdm.core.type.upath;

import org.datasphere.mdm.core.type.data.DataRecord;

/**
 * @author Mikhail Mikhailov on Feb 25, 2021
 * Filtering element interface.
 */
public interface UPathFilterElement extends UPathElement {
    /**
     * {@inheritDoc}
     */
    @Override
    default boolean isFiltering() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default UPathFilterElement getFilter() {
        return this;
    }
    /**
     * Returns true, if the given record matches filtering condition.
     * @param record the record to check
     * @return true, if the given record matches filtering condition
     */
    boolean matches(DataRecord record);
}
