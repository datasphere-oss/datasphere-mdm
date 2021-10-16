

package org.datasphere.mdm.core.type.job;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JobExecution {

    private Long id;

    private Long jobDefinitionId;

    private String jobName;

    private Instant startTime;

    private Instant createTime;

    private Instant endTime;

    private Instant lastUpdated;

    private JobExecutionState state;

    private boolean restartable;

    private List<JobExecutionStep> jobExecutionSteps = new ArrayList<>();

    public JobExecution() {
        super();
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the jobDefinitionId
     */
    public Long getJobDefinitionId() {
        return jobDefinitionId;
    }
    /**
     * @param jobDefinitionId the jobDefinitionId to set
     */
    public void setJobDefinitionId(Long jobDefinitionId) {
        this.jobDefinitionId = jobDefinitionId;
    }
    /**
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }
    /**
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Instant getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }
    public Instant getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }
    public Instant getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
    public Instant getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    public JobExecutionState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(JobExecutionState state) {
        this.state = state;
    }

    public Collection<JobExecutionStep> getJobExecutionSteps() {
        return jobExecutionSteps;
    }
    /**
     * @return the restartable
     */
    public boolean isRestartable() {
        return restartable;
    }
    /**
     * @param restartable the restartable to set
     */
    public void setRestartable(boolean restartable) {
        this.restartable = restartable;
    }
}
