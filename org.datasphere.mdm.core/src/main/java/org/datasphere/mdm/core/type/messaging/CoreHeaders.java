
package org.datasphere.mdm.core.type.messaging;

import org.datasphere.mdm.system.type.messaging.Header;
import org.datasphere.mdm.system.type.messaging.Header.HeaderType;

/**
 * @author Mikhail Mikhailov on Jul 10, 2020
 */
public class CoreHeaders {
    /**
     * Disabling constructor.
     */
    private CoreHeaders() {
        super();
    }
    /**
     * Role name.
     */
    public static final Header ROLE_NAME = new Header("role", HeaderType.STRING);
    /**
     * Label name.
     */
    public static final Header LABEL_NAME = new Header("label", HeaderType.STRING);
    /**
     * Security action reason.
     */
    public static final Header ACTION_REASON = new Header("reason", HeaderType.STRING);
}
