
package org.datasphere.mdm.core.dto.job;

import java.util.Objects;

import org.datasphere.mdm.core.type.job.JobExecution;

/**
 * @author Mikhail Mikhailov on Jul 6, 2021
 */
public class JobExecutionStartResult {
    /**
     * The execution.
     */
    private JobExecution execution;
    /**
     * Constructor.
     */
    public JobExecutionStartResult() {
        super();
    }
    /**
     * Constructor.
     */
    public JobExecutionStartResult(JobExecution execution) {
        super();
        this.execution = execution;
    }
    /**
     * @return the execution
     */
    public JobExecution getExecution() {
        return execution;
    }
    /**
     * @param execution the execution to set
     */
    public void setExecution(JobExecution execution) {
        this.execution = execution;
    }
    /**
     * Returns true, if the execution is missing.
     * @return true, if the execution is missing
     */
    public boolean isEmpty() {
        return Objects.isNull(execution);
    }
}
