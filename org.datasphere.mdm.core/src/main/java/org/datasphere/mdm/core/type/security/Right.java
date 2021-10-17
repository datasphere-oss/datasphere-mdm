

package org.datasphere.mdm.core.type.security;

/**
 * @author Denis Kostovarov
 */
public interface Right {
    SecuredResource getSecuredResource();

    boolean isCreate();

    boolean isUpdate();

    boolean isDelete();

    boolean isRead();
}
