
package org.datasphere.mdm.core.type.job;

/**
 * @author Mikhail Mikhailov on Jul 6, 2021
 * Execution state.
 */
public abstract class AbstractExecutionState<X extends AbstractExecutionState<X>> {
    /**
     * Exit code (if finished).
     */
    private String exitCode;
    /**
     * Exit description (if finished).
     */
    private String exitDescription;
    /**
     * Status of the execution.
     */
    private JobExecutionStatus status;
    /**
     * Constructor.
     * @param exitCode
     * @param exitDescription
     */
    protected AbstractExecutionState() {
        super();
    }
    /**
     * @return the status
     */
    public JobExecutionStatus getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(JobExecutionStatus status) {
        this.status = status;
    }
    /**
     * @return the exit code
     */
    public String getExitCode() {
        return exitCode;
    }
    /**
     * @param exitCode the exitCode to set
     */
    public void setExitCode(String exitCode) {
        this.exitCode = exitCode;
    }
    /**
     * @return the exit description.
     */
    public String getExitDescription() {
        return exitDescription;
    }
    /**
     * @param exitDescription the exitDescription to set
     */
    public void setExitDescription(String exitDescription) {
        this.exitDescription = exitDescription;
    }

    public X withStatus(JobExecutionStatus status) {
        setStatus(status);
        return self();
    }

    public X withExitDescription(String exitDescription) {
        setExitDescription(exitDescription);
        return self();
    }

    public X withExitCode(String exitCode) {
        setExitCode(exitCode);
        return self();
    }

    @SuppressWarnings("unchecked")
    protected X self() {
        return (X) this;
    }
}
