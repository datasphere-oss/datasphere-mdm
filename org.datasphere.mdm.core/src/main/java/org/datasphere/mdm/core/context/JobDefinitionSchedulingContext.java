
package org.datasphere.mdm.core.context;

/**
 * @author Mikhail Mikhailov on Jul 5, 2021
 */
public class JobDefinitionSchedulingContext extends AbstractJobDefinitionContext {
    /**
     * The cron expression.
     */
    private final String cronExpression;
    /**
     * Constructor.
     */
    private JobDefinitionSchedulingContext(JobDefinitionSchedulingContextBuilder b) {
        super(b);
        this.cronExpression = b.cronExpression;
    }
    /**
     * @return the cronExpression
     */
    public String getCronExpression() {
        return cronExpression;
    }
    /**
     * Builder instance.
     * @return builder instance
     */
    public static JobDefinitionSchedulingContextBuilder builder() {
        return new JobDefinitionSchedulingContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Jul 6, 2021
     * Builder.
     */
    public static class JobDefinitionSchedulingContextBuilder extends AbstractJobDefinitionContextBuilder<JobDefinitionSchedulingContextBuilder> {
        /**
         * The cron expression.
         */
        private String cronExpression;

        private JobDefinitionSchedulingContextBuilder() {
            super();
        }

        public JobDefinitionSchedulingContextBuilder cronExpression(String cronExpression) {
            this.cronExpression = cronExpression;
            return this;
        }

        @Override
        public JobDefinitionSchedulingContext build() {
            return new JobDefinitionSchedulingContext(this);
        }
    }
}
