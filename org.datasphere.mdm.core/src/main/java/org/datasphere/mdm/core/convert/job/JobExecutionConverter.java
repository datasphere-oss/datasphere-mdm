
package org.datasphere.mdm.core.convert.job;

import java.util.Objects;

import org.datasphere.mdm.core.type.job.JobExecution;
import org.datasphere.mdm.core.type.job.JobExecutionState;
import org.datasphere.mdm.core.type.job.JobExecutionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.datasphere.mdm.system.convert.Converter;

/**
 * @author Mikhail Mikhailov on Jul 7, 2021
 */
@Component
public class JobExecutionConverter extends Converter<org.springframework.batch.core.JobExecution, JobExecution> {

    @Autowired
    private StepExecutionConverter stepExecutionConverter;

    /**
     * Constructor.
     */
    public JobExecutionConverter() {
        super();
        this.to = this::convert;
    }

    private JobExecution convert(org.springframework.batch.core.JobExecution source) {

        JobExecution target = new JobExecution();
        target.setId(source.getId());
        target.setJobName(source.getJobInstance().getJobName());
        target.setStartTime(Objects.isNull(source.getStartTime()) ? null : source.getStartTime().toInstant());
        target.setCreateTime(Objects.isNull(source.getCreateTime()) ? null : source.getCreateTime().toInstant());
        target.setLastUpdated(Objects.isNull(source.getLastUpdated()) ? null : source.getLastUpdated().toInstant());
        target.setEndTime(Objects.isNull(source.getEndTime()) ? null : source.getEndTime().toInstant());
        target.setState(new JobExecutionState()
                .withStatus(JobExecutionStatus.valueOf(source.getStatus().name()))
                .withExitDescription(source.getExitStatus().getExitDescription())
                .withExitCode(source.getExitStatus().getExitCode()));
        target.getJobExecutionSteps().addAll(stepExecutionConverter.to(source.getStepExecutions()));

        return target;
    }


}
