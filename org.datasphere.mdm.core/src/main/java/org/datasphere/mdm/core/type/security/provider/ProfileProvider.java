

package org.datasphere.mdm.core.type.security.provider;

import org.datasphere.mdm.core.type.security.User;

/**
 * @author Denis Kostovarov
 */
public interface ProfileProvider {
    /**
     * Adds profile information to the user object.
     * @param user the user object
     */
    void load(User user);
}
