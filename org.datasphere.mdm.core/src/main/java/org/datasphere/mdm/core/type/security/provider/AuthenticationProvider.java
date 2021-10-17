

package org.datasphere.mdm.core.type.security.provider;

import org.datasphere.mdm.core.context.AuthenticationRequestContext;
import org.datasphere.mdm.core.type.security.User;

/**
 * @author Denis Kostovarov
 */
@FunctionalInterface
public interface AuthenticationProvider {
    /**
     * Login a user.
     * @param context system params
     * @return User
     */
    User login(AuthenticationRequestContext context);
}
