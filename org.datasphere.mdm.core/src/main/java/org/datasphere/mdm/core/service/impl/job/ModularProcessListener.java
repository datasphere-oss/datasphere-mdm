
package org.datasphere.mdm.core.service.impl.job;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.type.job.JobFraction;
import org.springframework.batch.core.ItemProcessListener;

/**
 * @author Mikhail Mikhailov on Jul 12, 2021
 */
@SuppressWarnings("rawtypes")
public class ModularProcessListener extends ModularStepListenerSupport implements ItemProcessListener {
    /**
     * Constructor.
     * @param jobName the job name
     * @param stepName the step name
     */
    public ModularProcessListener(String jobName, String stepName) {
        super(jobName, stepName);
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void beforeProcess(Object item) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            ItemProcessListener ipl = fraction.itemProcessListener(getStepName());
            if (Objects.isNull(ipl)) {
                continue;
            }

            ipl.beforeProcess(item);
        }
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void afterProcess(Object item, Object result) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            ItemProcessListener ipl = fraction.itemProcessListener(getStepName());
            if (Objects.isNull(ipl)) {
                continue;
            }

            ipl.afterProcess(item, result);
        }
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onProcessError(Object item, Exception e) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            ItemProcessListener ipl = fraction.itemProcessListener(getStepName());
            if (Objects.isNull(ipl)) {
                continue;
            }

            ipl.onProcessError(item, e);
        }
    }
}
