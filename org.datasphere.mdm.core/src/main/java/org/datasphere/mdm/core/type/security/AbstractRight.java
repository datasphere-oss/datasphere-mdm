

package org.datasphere.mdm.core.type.security;

import java.io.Serializable;

/**
 * @author Denis Kostovarov
 */
public abstract class AbstractRight implements Right, Serializable {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -5229596309091717180L;
    private SecuredResource securedResource;
    private boolean create;
    private boolean update;
    private boolean delete;
    private boolean read;
    @Override
    public SecuredResource getSecuredResource() {
        return securedResource;
    }

    public void setSecuredResource(SecuredResource securedResource) {
        this.securedResource = securedResource;
    }

    @Override
    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    @Override
    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    @Override
    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
