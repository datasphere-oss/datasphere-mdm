
package org.datasphere.mdm.core.service.job.step;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author Mikhail Mikhailov on Jul 13, 2021
 * Step listener adapter.
 */
public class StepExecutionListenerAdapter implements StepExecutionListener {
    /**
     * Constructor.
     */
    public StepExecutionListenerAdapter() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeStep(StepExecution stepExecution) {
        // Override
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // Override
        return stepExecution.getExitStatus();
    }
}
