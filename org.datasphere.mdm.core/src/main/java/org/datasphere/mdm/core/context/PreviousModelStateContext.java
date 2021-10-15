
package org.datasphere.mdm.core.context;

import org.datasphere.mdm.core.type.model.ModelInstance;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * @author Mikhail Mikhailov on Nov 2, 2020
 * Previous model state holder.
 */
public interface PreviousModelStateContext extends StorageCapableContext {
    /**
     * The notification id SID.
     */
    StorageId SID_PREVIOUS_MODEL_STATE = new StorageId("PREVIOUS_MODEL_STATE");
    /**
     * Gets state.
     * @param <M> the expected model type
     * @return state or null
     */
    default <M extends ModelInstance<?>> M prevousState() {
        return getFromStorage(SID_PREVIOUS_MODEL_STATE);
    }
    /**
     * Puts state.
     * @param <M> the expected model type
     * @param m the state
     */
    default <M extends ModelInstance<?>> void prevousState(M m) {
        putToStorage(SID_PREVIOUS_MODEL_STATE, m);
    }
}
