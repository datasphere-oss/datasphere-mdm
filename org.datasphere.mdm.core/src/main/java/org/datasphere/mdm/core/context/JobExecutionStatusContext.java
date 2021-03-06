
package org.datasphere.mdm.core.context;

/**
 * @author Mikhail Mikhailov on Jul 7, 2021
 * Execution query context
 */
public class JobExecutionStatusContext extends AbstractJobDefinitionContext {
    /**
     * The job name.
     * This can stop a system job.
     */
    private final String jobName;
    /**
     * The job execution id, that can be used for stopping of a job.
     */
    private final Long jobExecutionId;
    /**
     * Restart a job
     */
    private final boolean progress;
    /**
     * Constructor.
     * @param b
     */
    private JobExecutionStatusContext(JobExecutionStatusContextBuilder b) {
        super(b);
        this.jobName = b.jobName;
        this.jobExecutionId = b.jobExecutionId;
        this.progress = b.progress;
    }
    /**
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }
    /**
     * @return the jobExecutionId
     */
    public Long getJobExecutionId() {
        return jobExecutionId;
    }
    /**
     * @return the progress
     */
    public boolean isProgress() {
        return progress;
    }
    /**
     * The builder.
     * @return builder
     */
    public static JobExecutionStatusContextBuilder builder() {
        return new JobExecutionStatusContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Jul 7, 2021
     * Job execution query builder.
     */
    public static class JobExecutionStatusContextBuilder extends AbstractJobDefinitionContextBuilder<JobExecutionStatusContextBuilder> {
        /**
         * The job name.
         * This can start a job without any definitions just using the supplied parameters.
         */
        private String jobName;
        /**
         * The job execution id, that can be used for stopping of a job.
         */
        private Long jobExecutionId;
        /**
         * Track progress.
         */
        private boolean progress;
        /**
         * Constructor.
         */
        private JobExecutionStatusContextBuilder() {
            super();
        }
        /**
         * Sets job name.
         * @param jobName the job name
         * @return self
         */
        public JobExecutionStatusContextBuilder jobName(String jobName) {
            this.jobName = jobName;
            return self();
        }
        /**
         * Sets the execution id to stop.
         * @param jobExecutionId the job execution id to stop
         * @return self
         */
        public JobExecutionStatusContextBuilder jobExecutionId(Long jobExecutionId) {
            this.jobExecutionId = jobExecutionId;
            return self();
        }
        /**
         * Track progress flag.
         * @param progress the track state
         * @return self
         */
        public JobExecutionStatusContextBuilder progress(boolean progress) {
            this.progress = progress;
            return self();
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public JobExecutionStatusContext build() {
            return new JobExecutionStatusContext(this);
        }
    }
}
