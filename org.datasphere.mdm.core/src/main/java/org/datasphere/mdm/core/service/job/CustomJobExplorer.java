
package org.datasphere.mdm.core.service.job;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.datasphere.mdm.core.dto.job.JobPaginatedResult;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;

/**
 * @author Mikhail Mikhailov on Jul 6, 2021
 * Extension to SB job explorer.
 */
public interface CustomJobExplorer extends JobExplorer {
    /**
     * Does cleanup (removes instances and all dependent objects) for the given instance ids
     * @param jobInstanceIds the instance IDs
     */
    void cleanupJobExecutions(Collection<Long> jobInstanceIds);
    /**
     * Gets job execution objects for the given instance IDs.
     * @param jobInstanceIds the instance IDs
     * @return executions map with instance ID keys
     */
    Map<Long, List<JobExecution>> getJobExecutions(Collection<Long> jobInstanceIds);
    /**
     * Gets last (latest) job executions for the given collection of job instance IDs.
     * @param jobInstanceIds instance IDs
     * @param loadSteps indicator flag, telling whether to load the content of executions
     * @return map, where keys are instance IDs and values are latest executions
     */
    Map<Long, JobExecution> getLastJobExecutions(Collection<Long> jobInstanceIds, boolean loadSteps);
    /**
     * Gets last (latest) job execution IDs for the given collection of job instance IDs.
     * @param jobInstanceIds instance IDs
     * @return map, where keys are instance IDs and values are latest execution IDs
     */
    Map<Long, Long> getLastJobExecutionIds(Collection<Long> jobInstanceIds);
    /**
     * Searches for job executions by given criteria.
     * @param filter the search criteria
     * @return search result
     */
    JobPaginatedResult<JobExecution> searchJobExecutions(JobExecutionFilter filter);
    /**
     * Searches for step executions by given criteria.
     * @param filter the search criteria
     * @return reesult
     */
    JobPaginatedResult<StepExecution> searchStepExecutions(StepExecutionFilter filter);
    /**
     * Gets the date of the last successful execution, searching in the given collection of instance IDs.
     * @param jobInstanceIds the instance IDs to examine
     * @return date or null
     */
    @Nullable
    Date getLastSuccessJobExecutionsDate(Collection<Long> jobInstanceIds);
}
