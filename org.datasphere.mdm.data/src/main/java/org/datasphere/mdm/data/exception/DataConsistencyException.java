

package org.datasphere.mdm.data.exception;

import java.util.Collection;

import org.datasphere.mdm.system.exception.DomainId;
import org.datasphere.mdm.system.exception.ExceptionId;
import org.datasphere.mdm.system.exception.PlatformValidationException;
import org.datasphere.mdm.system.exception.ValidationResult;

public class DataConsistencyException extends PlatformValidationException {
    /**
     * This exception domain.
     */
    private static final DomainId DATA_CONSISTENCY_EXCEPTION = () -> "DATA_CONSISTENCY_EXCEPTION";
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -884715053710999194L;
    /**
     * Constructor.
     * @param message
     * @param id
     * @param validationResult
     * @param args
     */
    public DataConsistencyException(String message, ExceptionId id, Collection<ValidationResult> validationResult, Object... args) {
        super(message, id, validationResult, args);
    }
    /**
     * Constructor.
     * @param message
     * @param id
     * @param validationResults
     */
    public DataConsistencyException(String message, ExceptionId id, Collection<ValidationResult> validationResults) {
        super(message, id, validationResults);
    }
    /**
     * Constructor.
     * @param message
     * @param cause
     * @param id
     * @param validationResult
     * @param args
     */
    public DataConsistencyException(String message, Throwable cause, ExceptionId id,
            Collection<ValidationResult> validationResult, Object... args) {
        super(message, cause, id, validationResult, args);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DomainId getDomain() {
        return DATA_CONSISTENCY_EXCEPTION;
    }
}
