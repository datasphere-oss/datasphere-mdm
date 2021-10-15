
package org.datasphere.mdm.core.context;

import org.datasphere.mdm.core.type.job.JobDefinition;

/**
 * @author Mikhail Mikhailov on Jun 18, 2021
 */
public class JobDefinitionUpsertContext {
    /**
     * Job definition (not null).
     */
    private final JobDefinition definition;
    /**
     * Fetch only active definitions.
     */
    private final boolean skipCronWarnings;
    /**
     * Check definitions without an id for being the same job
     * by checking name and reuse the existing id, if the name and jobName match.
     */
    private final boolean checkByName;
    /**
     * Constructor.
     */
    private JobDefinitionUpsertContext(JobDefinitionUpsertContextBuilder b) {
        super();
        this.definition = b.definition;
        this.skipCronWarnings = b.skipCronWarnings;
        this.checkByName = b.checkByName;
    }
    /**
     * @return the definition
     */
    public JobDefinition getDefinition() {
        return definition;
    }
    /**
     * @return the activeOnly
     */
    public boolean isSkipCronWarnings() {
        return skipCronWarnings;
    }
    /**
     * @return the checkByName
     */
    public boolean isCheckByName() {
        return checkByName;
    }
    /**
     * @return builder
     */
    public static JobDefinitionUpsertContextBuilder builder() {
        return new JobDefinitionUpsertContextBuilder();
    }
    /**
     * @author Mikhail Mikhailov on Jun 18, 2021
     * Builder class.
     */
    public static class JobDefinitionUpsertContextBuilder {
        /**
         * Job definition (not null).
         */
        private JobDefinition definition;
        /**
         * Fetch only active definitions.
         */
        private boolean skipCronWarnings;
        /**
         * Check definitions without an id for being the same job
         * by checking name and reuse the existing id, if the name and jobName match.
         */
        private boolean checkByName;
        /**
         * Constructor.
         */
        private JobDefinitionUpsertContextBuilder() {
            super();
        }
        /**
         * Skip cron warnings during validation.
         * @param value the mark
         * @return self
         */
        public JobDefinitionUpsertContextBuilder skipCronWarnings(boolean value) {
            this.skipCronWarnings = value;
            return this;
        }
        /**
         * Check definitions without an id for being the same job
         * by checking name and reuse the existing id, if the name and jobName match.
         * @param value the mark
         * @return self
         */
        public JobDefinitionUpsertContextBuilder checkByName(boolean value) {
            this.checkByName = value;
            return this;
        }
        /**
         * Sets job definition to save.
         * @param value the definition
         * @return self
         */
        public JobDefinitionUpsertContextBuilder definition(JobDefinition definition) {
            this.definition = definition;
            return this;
        }
        /**
         * Builder method.
         * @return context
         */
        public JobDefinitionUpsertContext build() {
            return new JobDefinitionUpsertContext(this);
        }
    }
}
