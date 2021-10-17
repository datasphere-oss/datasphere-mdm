
package org.datasphere.mdm.data.exception;

import org.datasphere.mdm.system.exception.DomainId;
import org.datasphere.mdm.system.exception.ExceptionId;
import org.datasphere.mdm.system.exception.PlatformRuntimeException;

/**
 * @author Mikhail Mikhailov on Oct 23, 2019
 */
public class DataProcessingException extends PlatformRuntimeException {
    /**
     * Exception domain id.
     */
    private static final DomainId DATA_PROCESSING_EXCEPTION = () -> "DATA_PROCESSING_EXCEPTION";
    /**
     * SVUID.
     */
    private static final long serialVersionUID = 7458292534250475891L;
    /**
     * Constructor.
     * @param message
     * @param id
     * @param args
     */
    public DataProcessingException(String message, ExceptionId id, Object... args) {
        super(message, id, args);
    }
    /**
     * Constructor.
     * @param message
     * @param cause
     * @param id
     * @param args
     */
    public DataProcessingException(String message, Throwable cause, ExceptionId id, Object... args) {
        super(message, cause, id, args);
    }
    /**
     * Constructor.
     * @param cause
     * @param id
     * @param args
     */
    public DataProcessingException(Throwable cause, ExceptionId id, Object... args) {
        super(cause, id, args);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DomainId getDomain() {
        return DATA_PROCESSING_EXCEPTION;
    }
}
