
package org.datasphere.mdm.core.service.impl.job;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.type.job.JobFraction;
import org.springframework.batch.core.ItemWriteListener;

/**
 * @author Mikhail Mikhailov on Jul 12, 2021
 */
@SuppressWarnings("rawtypes")
public class ModularWriteListener extends ModularStepListenerSupport implements ItemWriteListener {
    /**
     * Constructor.
     * @param jobName the job name
     * @param stepName the step name
     */
    public ModularWriteListener(String jobName, String stepName) {
        super(jobName, stepName);
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void beforeWrite(List items) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            ItemWriteListener iwl = fraction.itemWriteListener(getStepName());
            if (Objects.isNull(iwl)) {
                continue;
            }

            iwl.beforeWrite(items);
        }
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void afterWrite(List items) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            ItemWriteListener iwl = fraction.itemWriteListener(getStepName());
            if (Objects.isNull(iwl)) {
                continue;
            }

            iwl.afterWrite(items);
        }
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onWriteError(Exception exception, List items) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            ItemWriteListener iwl = fraction.itemWriteListener(getStepName());
            if (Objects.isNull(iwl)) {
                continue;
            }

            iwl.onWriteError(exception, items);
        }
    }
}
