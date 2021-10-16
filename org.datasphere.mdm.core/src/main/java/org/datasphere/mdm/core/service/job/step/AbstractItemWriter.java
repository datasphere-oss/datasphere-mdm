
package org.datasphere.mdm.core.service.job.step;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Mikhail Mikhailov on Feb 17, 2020
 */
public abstract class AbstractItemWriter<T> implements ItemWriter<T> {
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
