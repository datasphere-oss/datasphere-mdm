

package org.datasphere.mdm.data.context;

/**
 * @author Mikhail Mikhailov
 * An opportunity to (re)set the external id for a context.
 */
public interface ExternalIdResettingContext extends RecordIdentityContext {
    /**
     * (Re)sets the external id on a context.
     * @param externalId the id to set
     */
    void setExternalId(String externalId);
}
