

package org.datasphere.mdm.core.type.security;

/**
 * @author Mikhail Mikhailov
 *         Keys for opaque authentication system parameters.
 */
public enum AuthenticationSystemParameter {
    /**
     * User name.
     */
    PARAM_USER_NAME,
    /**
     * Token.
     */
    PARAM_USER_TOKEN,
    /**
     * External token.
     */
    PARAM_EXTERNAL_TOKEN,
    /**
     * User password.
     */
    PARAM_USER_PASSWORD,
    /**
     * User locale.
     */
    PARAM_USER_LOCALE,
    /**
     * Request
     */
    PARAM_HTTP_SERVLET_REQUEST,
    /**
     * Client ip
     */
    PARAM_CLIENT_IP,
    /**
     * Server ip
     */
    PARAM_SERVER_IP,
    /**
     * Endpoint
     */
    PARAM_ENDPOINT,
    /**
     * Details
     */
    PARAM_DETAILS
}
