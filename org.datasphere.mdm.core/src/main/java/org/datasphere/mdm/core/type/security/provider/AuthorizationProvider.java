

package org.datasphere.mdm.core.type.security.provider;

import org.datasphere.mdm.core.type.security.User;

/**
 * @author Denis Kostovarov
 */
public interface AuthorizationProvider {
    /**
     * Calls authorization. procedure.
     * @param user the user
     */
    void authorize(User user);
}
