

package org.datasphere.mdm.core.dto.job;

import java.util.List;

import org.datasphere.mdm.core.type.job.JobExecutionStep;

/**
 * @author Mikhail Mikhailov
 * Steps page.
 */
public class StepExecutionQueryResult extends JobPaginatedResult<JobExecutionStep> {
    /**
     * Constructor.
     */
    public StepExecutionQueryResult() {
        super();
    }

    public StepExecutionQueryResult(List<JobExecutionStep> page) {
        super(page);
    }

    public StepExecutionQueryResult(List<JobExecutionStep> page, int totalCount) {
        super(page, totalCount);
    }

    public StepExecutionQueryResult(List<JobExecutionStep> page, int totalCount, int finishedCount) {
        super(page, totalCount, finishedCount);
    }
}
