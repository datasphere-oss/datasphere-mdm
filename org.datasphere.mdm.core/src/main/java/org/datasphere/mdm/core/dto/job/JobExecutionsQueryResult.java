

package org.datasphere.mdm.core.dto.job;

import java.util.List;

import org.datasphere.mdm.core.type.job.JobExecution;

/**
 * @author Mikhail Mikhailov
 * Paginated execution state of a job.
 */
public class JobExecutionsQueryResult extends JobPaginatedResult<JobExecution> {
    /**
     * Constructor.
     */
    public JobExecutionsQueryResult() {
        super();
    }
    /**
     * Constructor.
     * @param page
     */
    public JobExecutionsQueryResult(List<JobExecution> page) {
        super(page);
    }
    /**
     * Constructor.
     * @param page
     * @param totalCount
     */
    public JobExecutionsQueryResult(List<JobExecution> page, int totalCount) {
        super(page, totalCount);
    }
    /**
     * Constructor.
     * @param page
     * @param totalCount
     * @param finishedCount
     */
    public JobExecutionsQueryResult(List<JobExecution> page, int totalCount, int finishedCount) {
        super(page, totalCount, finishedCount);
    }
}
