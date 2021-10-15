
package org.datasphere.mdm.core.context;

/**
 * @author Mikhail Mikhailov on Jul 8, 2021
 */
public class StepExecutionQueryContext {
    /**
     * The execution id.
     */
    private final Long jobExecutionId;
    /**
     * The instance id
     */
    private final Long jobInstanceId;
    /**
     * Start id.
     */
    private final Long from;
    /**
     * Count
     */
    private final Integer count;
    /**
     * Constructor.
     */
    private StepExecutionQueryContext(StepExecutionQueryContextBuilder b) {
        super();
        this.jobExecutionId = b.jobExecutionId;
        this.jobInstanceId = b.jobInstanceId;
        this.from = b.from;
        this.count = b.count;
    }
    /**
     * @return the jobExecutionId
     */
    public Long getJobExecutionId() {
        return jobExecutionId;
    }
    /**
     * @return the jobInstanceId
     */
    public Long getJobInstanceId() {
        return jobInstanceId;
    }
    /**
     * @return the from
     */
    public Long getFrom() {
        return from;
    }
    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }
    /**
     * Builder rmethod.
     * @return builder
     */
    public static StepExecutionQueryContextBuilder builder() {
        return new StepExecutionQueryContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Jul 8, 2021
     * Builder.
     */
    public static class StepExecutionQueryContextBuilder {
        /**
         * The execution id.
         */
        private Long jobExecutionId;
        /**
         * The instance id
         */
        private Long jobInstanceId;
        /**
         * Start id.
         */
        private Long from;
        /**
         * Count
         */
        private Integer count;
        /**
         * Constructor.
         */
        private StepExecutionQueryContextBuilder() {
            super();
        }
        /**
         * Sets execution id.
         * @param jobExecutionId
         * @return self
         */
        public StepExecutionQueryContextBuilder jobExecutionId(Long jobExecutionId) {
            this.jobExecutionId = jobExecutionId;
            return this;
        }
        /**
         * Sets instance id.
         * @param jobInstanceId
         * @return self
         */
        public StepExecutionQueryContextBuilder jobInstanceId(Long jobInstanceId) {
            this.jobInstanceId = jobInstanceId;
            return this;
        }
        /**
         * Sets the from index.
         * @param from the from index
         * @return self
         */
        public StepExecutionQueryContextBuilder from(Long from) {
            this.from = from;
            return this;
        }
        /**
         * Sets the return count.
         * @param count the return count
         * @return self
         */
        public StepExecutionQueryContextBuilder count(Integer count) {
            this.count = count;
            return this;
        }
        /**
         * Instance.
         * @return instance
         */
        public StepExecutionQueryContext build() {
            return new StepExecutionQueryContext(this);
        }
    }
}
