
package org.datasphere.mdm.core.dto.job;

import org.datasphere.mdm.core.type.job.JobDefinition;

/**
 * @author Mikhail Mikhailov on Jun 24, 2021
 */
public class JobDefinitionUpsertResult {
    /**
     * The definitions.
     */
    private JobDefinition definition;
    /**
     * Constructor.
     */
    public JobDefinitionUpsertResult() {
        super();
    }
    /**
     * Constructor.
     */
    public JobDefinitionUpsertResult(JobDefinition definition) {
        super();
        this.definition = definition;
    }
    /**
     * @return the definitions
     */
    public JobDefinition getDefinition() {
        return definition;
    }
    /**
     * @param definitions the definitions to set
     */
    public void setDefinition(JobDefinition definitions) {
        this.definition = definitions;
    }
}
