
package org.datasphere.mdm.core.service.impl.job;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.type.job.JobFraction;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author Mikhail Mikhailov on Jul 12, 2021
 */
public class ModularStepExecutionListener extends ModularStepListenerSupport implements StepExecutionListener {
    /**
     * Constructor.
     * @param jobName the job name
     * @param stepName the step name
     */
    public ModularStepExecutionListener(String jobName, String stepName) {
        super(jobName, stepName);
    }
    /**
     * Run fractions before step.
     * @param se the step execution
     */
    @Override
    public void beforeStep(StepExecution se) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            StepExecutionListener sel = fraction.stepExecutionListener(getStepName());
            if (Objects.isNull(sel)) {
                continue;
            }

            sel.beforeStep(se);
        }
    }
    /**
     * Run fractions after job.
     * @param se the job execution
     */
    @Override
    public ExitStatus afterStep(StepExecution se) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return se.getExitStatus();
        }

        for (JobFraction fraction : fractions) {

            StepExecutionListener sel = fraction.stepExecutionListener(getStepName());
            if (Objects.isNull(sel)) {
                continue;
            }

            sel.afterStep(se);
        }

        return se.getExitStatus();
    }
}
