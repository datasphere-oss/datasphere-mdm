
package org.datasphere.mdm.core.context;

/**
 * @author Mikhail Mikhailov on Oct 8, 2020
 * Model refresh marker, the model descriptor operates on.
 */
public interface ModelRefreshContext extends ModelIdentityContext {
    /**
     * Marks this efresh requets as either local or foreign.
     * @return true, if local, false otherwise
     */
    boolean isLocal();
}
