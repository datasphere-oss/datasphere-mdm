
package org.datasphere.mdm.core.service.impl.job;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.type.job.JobFraction;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author Mikhail Mikhailov on Jul 12, 2021
 */
public class ModularJobExecutionListener extends ModularJobListenerSupport implements JobExecutionListener {
    /**
     * Constructor.
     * @param jobName the job name
     */
    public ModularJobExecutionListener(String jobName) {
        super(jobName);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeJob(JobExecution je) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction mjf : fractions) {

            JobExecutionListener jel = mjf.jobExecutionListener();
            if (Objects.isNull(jel)) {
                continue;
            }

            jel.beforeJob(je);
        }
    }
    /**
     * Run fractions after job.
     * @param je the job execution
     */
    @Override
    public void afterJob(JobExecution je) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction mjf : fractions) {

            JobExecutionListener jel = mjf.jobExecutionListener();
            if (Objects.isNull(jel)) {
                continue;
            }

            jel.afterJob(je);
        }
    }
}
