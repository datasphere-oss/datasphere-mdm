

package org.datasphere.mdm.core.exception;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.datasphere.mdm.system.exception.DomainId;
import org.datasphere.mdm.system.exception.ExceptionId;
import org.datasphere.mdm.system.exception.PlatformRuntimeException;

/**
 * Job domain exceptions (thrown by the jobs subsys).
 * @author Alexander Magdenko
 */
public class JobException extends PlatformRuntimeException {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = 4401101538239705307L;
    /**
     * This exception domain.
     */
    private static final DomainId JOB_EXCEPTION = () -> "JOB_EXCEPTION";
    /**
     * Key-value pairs.
     */
    private final List<Pair<String, String>> params;
    /**
     * @param message
     */
    public JobException(String message, ExceptionId id, Object... args) {
        super(message, id, args);
        this.params = null;
    }
    /**
     * Validation error
     * @param message    Validation message.
     * @param id         Error id.
     * @param params     Params in error.
     * @param args       Params for message.
     */
    public JobException(String message, ExceptionId id, List<Pair<String, String>> params, Object... args) {
        super(message, id, args);
        this.params = params;
    }
    /**
     * @param message
     * @param cause
     */
    public JobException(String message, Throwable cause, ExceptionId id, Object... args) {
        super(message, cause, id, args);
        this.params = null;
    }

    /**
     * @param cause
     */
    public JobException(Throwable cause, ExceptionId id, Object... args) {
        super(cause, id, args);
        this.params = null;
    }
    /**
     * Gets the job params
     * @return params
     */
    public List<Pair<String, String>> getParams() {
        return params;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DomainId getDomain() {
        return JOB_EXCEPTION;
    }
}
