

package org.datasphere.mdm.data.context;

import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * Adds containment feature to relation contexts.
 * @author Mikhail Mikhailov on Nov 5, 2019
 */
public interface ContainmentRelationContext<T extends RecordIdentityContext> extends StorageCapableContext {
    /**
     * The containment.
     */
    StorageId SID_CONTAINMENT_CONTEXT = new StorageId("CONTAINMENT_CONTEXT");
    /**
     * Get containment context.
     * @return containment
     */
    default T containmentContext() {
        return getFromStorage(SID_CONTAINMENT_CONTEXT);
    }
    /**
     * Put containment context.
     * @param containment the containment
     */
    default void containmentContext(T containment) {
        putToStorage(SID_CONTAINMENT_CONTEXT, containment);
    }
}
