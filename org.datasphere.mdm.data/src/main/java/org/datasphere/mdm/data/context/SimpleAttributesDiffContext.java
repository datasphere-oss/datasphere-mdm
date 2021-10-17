

package org.datasphere.mdm.data.context;

import org.datasphere.mdm.core.type.data.impl.SimpleAttributesDiff;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * Simple diff to prev, diff to draft context.
 * @author Mikhail Mikhailov on Nov 1, 2019
 */
public interface SimpleAttributesDiffContext extends StorageCapableContext {
    /**
     * Diff to draft.
     */
    StorageId SID_DIFF_TO_DRAFT = new StorageId("DIFF_TO_DRAFT");
    /**
     * Diff to previous.
     */
    StorageId SID_DIFF_TO_PREVIOUS = new StorageId("DIFF_TO_PREVIOUS");
    /**
     * Gets diff to draft.
     * @return diff to draft
     */
    default SimpleAttributesDiff diffToDraft() {
        return getFromStorage(SID_DIFF_TO_DRAFT);
    }
    /**
     * Sets diff to draft.
     * @param diff to draft
     */
    default void diffToDraft(SimpleAttributesDiff diff) {
        putToStorage(SID_DIFF_TO_DRAFT, diff);
    }
    /**
     * Gets diff to previous.
     * @return diff to previous
     */
    default SimpleAttributesDiff diffToPrevious() {
        return getFromStorage(SID_DIFF_TO_PREVIOUS);
    }
    /**
     * Sets diff to previous.
     * @param diff to previous
     */
    default void diffToPrevious(SimpleAttributesDiff diff) {
        putToStorage(SID_DIFF_TO_PREVIOUS, diff);
    }
}
