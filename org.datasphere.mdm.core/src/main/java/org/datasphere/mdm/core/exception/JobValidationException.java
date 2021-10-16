
package org.datasphere.mdm.core.exception;

import java.util.Collection;

import org.datasphere.mdm.system.exception.DomainId;
import org.datasphere.mdm.system.exception.ExceptionId;
import org.datasphere.mdm.system.exception.PlatformValidationException;
import org.datasphere.mdm.system.exception.ValidationResult;

/**
 * @author Mikhail Mikhailov on Jun 29, 2021
 */
public class JobValidationException extends PlatformValidationException {
    /**
     * This exception domain.
     */
    private static final DomainId JOB_VALIDATION = () -> "JOB_VALIDATION";
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = 4056104613221814605L;
    /**
     * Constructor.
     * @param message
     * @param id
     * @param validationResult
     * @param args
     */
    public JobValidationException(String message, ExceptionId id, Collection<ValidationResult> validationResult,
            Object... args) {
        super(message, id, validationResult, args);
    }
    /**
     * Constructor.
     * @param message
     * @param cause
     * @param id
     * @param validationResult
     * @param args
     */
    public JobValidationException(String message, Throwable cause, ExceptionId id,
            Collection<ValidationResult> validationResult, Object... args) {
        super(message, cause, id, validationResult, args);
    }
    /**
     * Constructor.
     * @param message
     * @param id
     * @param validationResults
     */
    public JobValidationException(String message, ExceptionId id, Collection<ValidationResult> validationResults) {
        super(message, id, validationResults);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DomainId getDomain() {
        return JOB_VALIDATION;
    }
}
