
package org.datasphere.mdm.core.exception;

import org.datasphere.mdm.system.exception.ExceptionId;
import org.datasphere.mdm.system.exception.PlatformBusinessException;

/**
 * @author Mikhail Mikhailov
 * UPath exception.
 */
public class UPathException extends PlatformBusinessException {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = 5810613841042572108L;
    /**
     * Constructor.
     * @param message
     * @param id
     * @param args
     */
    public UPathException(String message, ExceptionId id, Object... args) {
        super(message, id, args);
    }
    /**
     * Constructor.
     * @param message
     * @param cause
     * @param id
     * @param args
     */
    public UPathException(String message, Throwable cause, ExceptionId id, Object... args) {
        super(message, cause, id, args);
    }

    public UPathException(Throwable cause, ExceptionId id, Object... args) {
        super("", cause, id, args);
    }
}
