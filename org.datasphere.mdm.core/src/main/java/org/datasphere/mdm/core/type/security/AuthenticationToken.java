

/**
 *
 */
package org.datasphere.mdm.core.type.security;

import java.util.Map;

/**
 * @author Mikhail Mikhailov
 * Authentication token.
 */
public interface AuthenticationToken {
    /**
     * @author mikhail
     * Additional params field names.
     */
    public enum SecurityParam {
        /**
         * Roles map, keyed with role name.
         */
        ROLES_MAP,
        /**
         * Rights map, keyed with resource names.
         */
        RIGHTS_MAP,
        /**
         * Labels map, keyed by resource name.
         */
        LABELS_MAP
    }
    /**
     * Gets user name.
     * @return user name
     */
    String getUserName();
    /**
     * Gets current authentication token.
     * @return the token
     */
    String getCurrentToken();
    /**
     * Gets additional parameters.
     * @return parameter map
     */
    Map<SecurityParam, Object> getSecurityParams();
    /**
     * Gets the user details.
     *
     * @return details
     */
    User getUserDetails();
}
