
package org.datasphere.mdm.core.context;

import java.util.Objects;

/**
 * @author Mikhail Mikhailov on Jul 4, 2021
 */
public class JobDefinitionsRemoveContext extends AbstractJobDefinitionsContext {
    /**
     * True for enable, false for disable.
     */
    private final boolean stop;
    /**
     * Constructor.
     */
    public JobDefinitionsRemoveContext(JobDefinitionsRemoveContextBuilder b) {
        super(b);
        this.stop = b.stop;
    }
    /**
     * @return the state
     */
    public boolean isStop() {
        return stop;
    }
    /**
     * @return builder
     */
    public static JobDefinitionsRemoveContextBuilder builder() {
        return new JobDefinitionsRemoveContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Jul 4, 2021
     * Builder.
     */
    public static class JobDefinitionsRemoveContextBuilder extends AbstractJobDefinitionsContextBuilder<JobDefinitionsRemoveContextBuilder> {
        /**
         * True for enable, false for disable.
         */
        private boolean stop;
        /**
         * Constructor.
         */
        private JobDefinitionsRemoveContextBuilder() {
            super();
        }
        /**
         * Stops forcibly all currently running job instances of this definitions.
         * @param stop the state
         * @return self
         */
        public JobDefinitionsRemoveContextBuilder stop(boolean stop) {
            this.stop = stop;
            return this;
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public JobDefinitionsRemoveContext build() {
            Objects.requireNonNull(this.jobDefinitionIds, "Job definition id(s) must not be null.");
            return new JobDefinitionsRemoveContext(this);
        }
    }
}
