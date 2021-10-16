
package org.datasphere.mdm.core.service.job.step;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Mikhail Mikhailov on Jul 13, 2021
 * Base item processor.
 */
public abstract class AbstractItemProcessor<I, O> implements ItemProcessor<I, O> {
    /**
     * The step execution.
     */
    @Value("#{stepExecution}")
    protected StepExecution stepExecution;
    /**
     * @return the stepName
     */
    public String getStepName() {
        return StringUtils.substringBefore(stepExecution.getStepName(), ":");
    }
    /**
     * @return the stepExecution
     */
    public StepExecution getStepExecution() {
        return stepExecution;
    }
}
