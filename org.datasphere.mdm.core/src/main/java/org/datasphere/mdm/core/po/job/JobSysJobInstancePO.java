

package org.datasphere.mdm.core.po.job;

import java.util.Date;

/**
 * @author Denis Kostovarov
 */
public class JobSysJobInstancePO {

    public static final String TABLE_NAME = "job_sys_job_instance";

    public static final String FIELD_JOB_DEFINITION_ID = "job_name";

    public static final String FIELD_JOB_INSTANCE_ID = "job_instance_id";

    private String jobName;

    private Long jobInstanceId;

    private Date createDate;

    private String createdBy;

    public JobSysJobInstancePO() {
        super();
    }

    public JobSysJobInstancePO(String jobName, Long jobInstanceId) {
        super();
        this.jobName = jobName;
        this.jobInstanceId = jobInstanceId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getJobInstanceId() {
        return jobInstanceId;
    }

    public void setJobInstanceId(Long jobInstanceId) {
        this.jobInstanceId = jobInstanceId;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
