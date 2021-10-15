
package org.datasphere.mdm.core.context;

import java.util.Objects;

/**
 * @author Mikhail Mikhailov on Jul 4, 2021
 */
public class JobDefinitionsMarkContext extends AbstractJobDefinitionsContext {
    /**
     * True for enable, false for disable.
     */
    private final boolean error;
    /**
     * Constructor.
     */
    public JobDefinitionsMarkContext(JobDefinitionsMarkContextBuilder b) {
        super(b);
        this.error = b.error;
    }
    /**
     * @return the state
     */
    public boolean isError() {
        return error;
    }
    /**
     * @return builder
     */
    public static JobDefinitionsMarkContextBuilder builder() {
        return new JobDefinitionsMarkContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Jul 4, 2021
     * Builder.
     */
    public static class JobDefinitionsMarkContextBuilder extends AbstractJobDefinitionsContextBuilder<JobDefinitionsMarkContextBuilder> {
        /**
         * True for enable, false for disable.
         */
        private boolean error;
        /**
         * Constructor.
         */
        private JobDefinitionsMarkContextBuilder() {
            super();
        }
        /**
         * Stops forcibly all currently running job instances of this definitions.
         * @param error the state
         * @return self
         */
        public JobDefinitionsMarkContextBuilder error(boolean error) {
            this.error = error;
            return this;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public JobDefinitionsMarkContext build() {
            Objects.requireNonNull(this.jobDefinitionIds, "Job definition id(s) must not be null.");
            return new JobDefinitionsMarkContext(this);
        }
    }
}
