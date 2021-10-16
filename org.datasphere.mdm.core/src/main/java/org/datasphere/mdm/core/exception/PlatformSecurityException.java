

package org.datasphere.mdm.core.exception;

import org.datasphere.mdm.system.exception.DomainId;
import org.datasphere.mdm.system.exception.ExceptionId;
import org.datasphere.mdm.system.exception.PlatformRuntimeException;

/**
 * @author Mikhail Mikhailov on Nov 6, 2019
 */
public class PlatformSecurityException extends PlatformRuntimeException {
    /**
     * This domain ID.
     */
    private static final DomainId SECURITY_EXCEPTION = () -> "SECURITY_EXCEPTION";
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = -2666211650358078564L;
    /**
     * Constructor.
     * @param message
     * @param id
     * @param args
     */
    public PlatformSecurityException(String message, ExceptionId id, Object... args) {
        super(message, id, args);
    }
    /**
     * Constructor.
     * @param message
     * @param cause
     * @param id
     * @param args
     */
    public PlatformSecurityException(String message, Throwable cause, ExceptionId id, Object... args) {
        super(message, cause, id, args);
    }
    /**
     * Constructor.
     * @param cause
     * @param id
     * @param args
     */
    public PlatformSecurityException(Throwable cause, ExceptionId id, Object... args) {
        super(cause, id, args);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DomainId getDomain() {
        return SECURITY_EXCEPTION;
    }

}
