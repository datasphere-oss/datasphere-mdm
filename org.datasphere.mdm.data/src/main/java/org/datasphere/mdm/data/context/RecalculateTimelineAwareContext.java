

package org.datasphere.mdm.data.context;

import org.datasphere.mdm.system.context.BooleanFlagsContext;

/**
 * Existing timeline processing without save actions.
 * @author Mikhail Mikhailov on Nov 21, 2019
 */
public interface RecalculateTimelineAwareContext extends BooleanFlagsContext {
    /**
     * Adds whole EXISTING timeline processing awareness to a context.
     * @return true for such operation, false otherwise
     */
    default boolean isRecalculateTimeline() {
        return getFlag(DataContextFlags.FLAG_RECALCULATE_WHOLE_TIMELINE);
    }
}
