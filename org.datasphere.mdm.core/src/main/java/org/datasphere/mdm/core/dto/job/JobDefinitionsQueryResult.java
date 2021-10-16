
package org.datasphere.mdm.core.dto.job;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.MapUtils;
import org.datasphere.mdm.core.type.job.JobDefinition;
import org.datasphere.mdm.core.type.job.JobExecutionState;

/**
 * @author Mikhail Mikhailov on Jun 24, 2021
 */
public class JobDefinitionsQueryResult {
    /**
     * The definitions.
     */
    private Map<JobDefinition, JobExecutionState> definitions;
    /**
     * Total count of all job definitions.
     */
    private int totalCount;
    /**
     * Constructor.
     */
    public JobDefinitionsQueryResult() {
        super();
    }
    /**
     * Constructor.
     */
    public JobDefinitionsQueryResult(Map<JobDefinition, JobExecutionState> definitions) {
        super();
        this.definitions = definitions;
    }
    /**
     * Constructor.
     * @param definitions
     * @param totalCount
     */
    public JobDefinitionsQueryResult(Map<JobDefinition, JobExecutionState> definitions, int totalCount) {
        this(definitions);
        this.totalCount = totalCount;
    }
    /**
     * @return the definitions
     */
    public Map<JobDefinition, JobExecutionState> getDefinitions() {
        return Objects.isNull(definitions) ? Collections.emptyMap() : definitions;
    }
    /**
     * @param definitions the definitions to set
     */
    public void setDefinitions(Map<JobDefinition, JobExecutionState> definitions) {
        this.definitions = definitions;
    }
    /**
     * Returns true, if empty.
     * @return true, if empty
     */
    public boolean isEmpty() {
        return MapUtils.isEmpty(definitions);
    }
    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }
    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
