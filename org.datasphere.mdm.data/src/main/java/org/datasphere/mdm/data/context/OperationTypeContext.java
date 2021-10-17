

package org.datasphere.mdm.data.context;

import org.datasphere.mdm.core.type.data.OperationType;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * @author Mikhail Mikhailov
 * Operation type indicator.
 */
public interface OperationTypeContext extends StorageCapableContext {
    /**
     * The operation type.
     */
    StorageId SID_OPERATION_TYPE = new StorageId("OPERATION_TYPE");
    /**
     * Get operation type
     * @return action
     */
    default OperationType operationType() {
        OperationType operationType = getFromStorage(SID_OPERATION_TYPE);
        return operationType == null ? OperationType.DIRECT : operationType;
    }
    /**
     * Put operation type
     * @param operationType the action
     */
    default void operationType(OperationType operationType) {
        putToStorage(SID_OPERATION_TYPE, operationType);
    }
}
