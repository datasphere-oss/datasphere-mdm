
package org.datasphere.mdm.core.context;

import java.util.Objects;

/**
 * @author Mikhail Mikhailov on Jul 4, 2021
 */
public class JobDefinitionsEnableContext extends AbstractJobDefinitionsContext {
    /**
     * True for enable, false for disable.
     */
    private final boolean enable;
    /**
     * Constructor.
     */
    public JobDefinitionsEnableContext(JobDefinitionsEnableContextBuilder b) {
        super(b);
        this.enable = b.enable;
    }
    /**
     * @return the state
     */
    public boolean isEnabled() {
        return enable;
    }
    /**
     * @return builder
     */
    public static JobDefinitionsEnableContextBuilder builder() {
        return new JobDefinitionsEnableContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Jul 4, 2021
     * Builder.
     */
    public static class JobDefinitionsEnableContextBuilder extends AbstractJobDefinitionsContextBuilder<JobDefinitionsEnableContextBuilder> {
        /**
         * True for enable, false for disable.
         */
        private boolean enable;
        /**
         * Constructor.
         */
        private JobDefinitionsEnableContextBuilder() {
            super();
        }
        /**
         * Enables or disables a job definition.
         * @param enable the state
         * @return self
         */
        public JobDefinitionsEnableContextBuilder enable(boolean enable) {
            this.enable = enable;
            return this;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public JobDefinitionsEnableContext build() {
            Objects.requireNonNull(this.jobDefinitionIds, "Job definition id(s) must not be null.");
            return new JobDefinitionsEnableContext(this);
        }
    }
}
