
package org.datasphere.mdm.core.convert.job;

import org.datasphere.mdm.core.type.job.JobExecutionStatus;
import org.datasphere.mdm.core.type.job.JobExecutionStep;
import org.datasphere.mdm.core.type.job.StepExecutionState;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.convert.Converter;

/**
 * @author Mikhail Mikhailov on Jul 8, 2021
 */
@Component
public class StepExecutionConverter extends Converter<StepExecution, JobExecutionStep> {

    /**
     * Constructor.
     * @param to
     * @param from
     */
    public StepExecutionConverter() {
        super(StepExecutionConverter::convert, null);
    }

    private static JobExecutionStep convert(final StepExecution source) {

        JobExecutionStep target = new JobExecutionStep();

        target.setId(source.getId());
        target.setJobExecutionId(source.getJobExecutionId());
        target.setStepName(source.getStepName());
        target.setStartTime(source.getStartTime() == null ? null : source.getStartTime().toInstant());
        target.setEndTime(source.getEndTime() == null ? null : source.getEndTime().toInstant());
        target.setLastUpdated(source.getLastUpdated() == null ? null : source.getLastUpdated().toInstant());
        target.setCommitCount(source.getCommitCount());
        target.setProcessSkipCount(source.getProcessSkipCount());
        target.setReadCount(source.getReadCount());
        target.setReadSkipCount(source.getReadSkipCount());
        target.setRollbackCount(source.getRollbackCount());
        target.setWriteCount(source.getWriteCount());
        target.setWriteSkipCount(source.getWriteSkipCount());
        target.setFailures(source.getFailureExceptions());
        target.setState(new StepExecutionState()
                .withStatus(JobExecutionStatus.valueOf(source.getStatus().name()))
                .withExitDescription(source.getExitStatus().getExitDescription())
                .withExitCode(source.getExitStatus().getExitCode()));

        return target;
    }
}
