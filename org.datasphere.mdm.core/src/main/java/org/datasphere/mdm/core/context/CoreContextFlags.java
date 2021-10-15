

package org.datasphere.mdm.core.context;

import org.datasphere.mdm.system.context.CommonRequestContext;

/**
 * @author Mikhail Mikhailov
 * Context flags.
 */
public final class CoreContextFlags {
    /**
     * Notification flag.
     */
    public static final int FLAG_SEND_NOTIFICATION = CommonRequestContext.FLAG_ID_PROVIDER.getAndIncrement();
    /**
     * Constructor.
     */
    private CoreContextFlags() {
        super();
    }
}
