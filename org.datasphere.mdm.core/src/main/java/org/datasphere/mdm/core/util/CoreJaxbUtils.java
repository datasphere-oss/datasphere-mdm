

package org.datasphere.mdm.core.util;

import org.datasphere.mdm.system.util.AbstractJaxbUtils;

/**
 * @author Mikhail Mikhailov on Oct 6, 2019
 * Basic JAXB stuff.
 */
public final class CoreJaxbUtils extends AbstractJaxbUtils {

    private static final com.datasphere.mdm.security.ObjectFactory SECURITY_FACTORY =
            new com.datasphere.mdm.security.ObjectFactory();

    /**
     * Constructor.
     */
    private CoreJaxbUtils() {
        super();
    }

    public static com.datasphere.mdm.security.ObjectFactory getSecurityFactory() {
        return SECURITY_FACTORY;
    }
}
