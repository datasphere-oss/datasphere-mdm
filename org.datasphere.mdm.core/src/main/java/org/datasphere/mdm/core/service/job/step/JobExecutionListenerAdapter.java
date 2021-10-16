
package org.datasphere.mdm.core.service.job.step;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author Mikhail Mikhailov on Jul 13, 2021
 * Job listener adapter.
 */
public class JobExecutionListenerAdapter implements JobExecutionListener {
    /**
     * Constructor.
     */
    public JobExecutionListenerAdapter() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {
        // Override
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        // Override
    }
}
