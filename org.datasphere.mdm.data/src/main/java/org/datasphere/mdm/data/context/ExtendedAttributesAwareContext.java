package org.datasphere.mdm.data.context;

import org.datasphere.mdm.system.context.BooleanFlagsContext;

/**
 * @author Mikhail Mikhailov on May 11, 2020
 */
public interface ExtendedAttributesAwareContext extends BooleanFlagsContext {
    /**
     * Adds request for extended attributes in the resulting timeline to a context.
     * @return true for extended attributes, false otherwise
     */
    default boolean isIncludeWinners() {
        return getFlag(DataContextFlags.FLAG_INCLUDE_WINNERS);
    }
}
