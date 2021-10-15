

package org.datasphere.mdm.core.context;

import java.util.Date;

/**
 * @author Mikhail Mikhailov
 * Adds mutability to validity range, needed in data upserts mostly.
 * It's a trade-off between immutability of the context and the need to modify period boundary according to metamodel in some cases.
 */
public interface MutableValidityRangeContext extends ValidityRangeContext {
    /**
     * Sets lower bound.
     * @param from the from timestamp
     */
    void setValidFrom(Date from);
    /**
     * Sets upper bound.
     * @param to the to timestamp
     */
    void setValidTo(Date to);
}
