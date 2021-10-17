package org.datasphere.mdm.data.context;

import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.data.type.merge.MergeMasterState;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;
import org.datasphere.mdm.system.type.pipeline.PipelineInput;

/**
 * @author Mikhail Mikhailov on May 5, 2020
 */
public interface MergeItemContext<S extends MergeMasterState> extends PipelineInput, StorageCapableContext {
    /**
     * Master keys.
     */
    StorageId SID_MASTER_KEYS = new StorageId("MASTER_KEYS");
    /**
     * Merge master state.
     */
    StorageId SID_MERGE_MASTER_STATE = new StorageId("MERGE_MASTER_STATE");
    /**
     * GET master key.
     * @return keys
     */
    default RecordKeys masterKeys() {
        return getFromStorage(SID_MASTER_KEYS);
    }
    /**
     * PUT master key.
     * @param keys
     */
    default void masterKeys(RecordKeys master) {
        putToStorage(SID_MASTER_KEYS, master);
    }
    /**
     * Get rel master state.
     * @return master state
     */
    default S masterState() {
        return getFromStorage(SID_MERGE_MASTER_STATE);
    }
    /**
     * Put rel master state.
     * @param set the master state
     */
    default void masterState(S state) {
        putToStorage(SID_MERGE_MASTER_STATE, state);
    }
}
