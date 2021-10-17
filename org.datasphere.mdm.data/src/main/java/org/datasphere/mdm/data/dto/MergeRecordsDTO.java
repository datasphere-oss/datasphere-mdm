

package org.datasphere.mdm.data.dto;


import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.system.dto.AbstractCompositeResult;
import org.datasphere.mdm.system.dto.ExecutionResult;
import org.datasphere.mdm.system.type.pipeline.PipelineOutput;

/**
 * @author Mikhail Mikhailov
 *
 */
public class MergeRecordsDTO
    extends AbstractCompositeResult
    implements PipelineOutput, ExecutionResult {

    /**
     * Operation successful, winner id is set.
     */
    private final RecordKeys winnerId;

    private final List<RecordKeys> mergedIds;

    private List<ErrorInfoDTO> errors;

    private boolean mergeWithConflicts = false;

    /**
     * Constructor.
     */
    public MergeRecordsDTO(RecordKeys winnerId, List<RecordKeys> mergedIds) {
        super();
        this.winnerId = winnerId;
        this.mergedIds = mergedIds;
    }

    /**
     * @return the winnerId
     */
    public RecordKeys getWinnerId() {
        return winnerId;
    }

    public List<RecordKeys> getMergedIds() {
        return Objects.isNull(mergedIds) ? Collections.emptyList() : mergedIds;
    }

    /**
     * list of errors
     */
    public List<ErrorInfoDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorInfoDTO> errors) {
        this.errors = errors;
    }

    public boolean isMergeWithConflicts() {
        return mergeWithConflicts;
    }

    public void setMergeWithConflicts(boolean mergeWithConflicts) {
        this.mergeWithConflicts = mergeWithConflicts;
    }
}
