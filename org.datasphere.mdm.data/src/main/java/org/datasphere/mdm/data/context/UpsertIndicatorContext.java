

package org.datasphere.mdm.data.context;

import org.datasphere.mdm.data.type.data.UpsertAction;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * @author Mikhail Mikhailov
 * Upsert state indicator.
 */
public interface UpsertIndicatorContext extends StorageCapableContext {
    /**
     * The exact upsert action.
     */
    StorageId SID_UPSERT_ACTION = new StorageId("UPSERT_ACTION");
    /**
     * Get upsert action
     * @return action
     */
    default UpsertAction upsertAction() {
        return getFromStorage(SID_UPSERT_ACTION);
    }
    /**
     * Put upsert action
     * @param action the action
     */
    default void upsertAction(UpsertAction action) {
        putToStorage(SID_UPSERT_ACTION, action);
    }
}
