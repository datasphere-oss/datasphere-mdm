

package org.datasphere.mdm.data.context;

import javax.annotation.Nullable;

import org.datasphere.mdm.core.type.security.Right;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;

/**
 * A context, holding access rights to the subject, it operates on (an entity, lookup, rel, clsf etc.).
 * @author Mikhail Mikhailov on Nov 6, 2019
 */
public interface AccessRightContext extends StorageCapableContext {
    /**
     * Access rights instance.
     */
    StorageId SID_ACCESS_RIGHT = new StorageId("ACCESS_RIGHT");
    /**
     * Gets access right.
     * @return right or null
     */
    @Nullable
    default<R extends Right> R accessRight() {
        return getFromStorage(SID_ACCESS_RIGHT);
    }
    /**
     * Sets access right for this context.
     * @param right the right
     */
    default void accessRight(Right right) {
        putToStorage(SID_ACCESS_RIGHT, right);
    }
}
