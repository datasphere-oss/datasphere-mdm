
package org.datasphere.mdm.core.service.impl.job;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.type.job.JobFraction;
import org.springframework.batch.core.ItemReadListener;

/**
 * @author Mikhail Mikhailov on Jul 12, 2021
 */
@SuppressWarnings("rawtypes")
public class ModularReadListener extends ModularStepListenerSupport implements ItemReadListener {
    /**
     * Constructor.
     * @param jobName the job name
     * @param stepName the step name
     */
    public ModularReadListener(String jobName, String stepName) {
        super(jobName, stepName);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeRead() {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            ItemReadListener<?> irl = fraction.itemReadListener(getStepName());
            if (Objects.isNull(irl)) {
                continue;
            }

            irl.beforeRead();
        }
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "unchecked" })
    @Override
    public void afterRead(Object item) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            ItemReadListener irl = fraction.itemReadListener(getStepName());
            if (Objects.isNull(irl)) {
                continue;
            }

            irl.afterRead(item);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onReadError(Exception ex) {

        Collection<JobFraction> fractions = getFractions();
        if (CollectionUtils.isEmpty(fractions)) {
            return;
        }

        for (JobFraction fraction : fractions) {

            ItemReadListener<?> irl = fraction.itemReadListener(getStepName());
            if (Objects.isNull(irl)) {
                continue;
            }

            irl.onReadError(ex);
        }
    }
}
