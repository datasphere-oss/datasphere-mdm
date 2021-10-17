

package org.datasphere.mdm.data.dto;


import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.data.type.keys.RelationKeys;
import org.datasphere.mdm.system.type.pipeline.PipelineOutput;

/**
 * @author Mikhail Mikhailov
 * Merge a single particular relation.
 */
public class MergeRelationDTO implements RelationDTO, PipelineOutput {
    /**
     * Operation successful, winner id is set.
     */
    private final RecordKeys winnerKeys;
    /**
     * The key of the merged relation.
     */
    private final RelationKeys relationKeys;
    /**
     * A possibly existing errors.
     */
    private List<ErrorInfoDTO> errors;
    /**
     * If there were conflicts during merge.
     */
    private boolean mergeWithConflicts;
    /**
     * Constructor.
     */
    public MergeRelationDTO(RecordKeys winnerKeys, RelationKeys mergedKeys) {
        super();
        this.winnerKeys = winnerKeys;
        this.relationKeys = mergedKeys;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public RelationKeys getRelationKeys() {
        return relationKeys;
    }
    /**
     * @return the winnerId
     */
    public RecordKeys getWinnerKeys() {
        return winnerKeys;
    }
    /**
     * Gets the list of errors.
     * @return list of errors.
     */
    public List<ErrorInfoDTO> getErrors() {
        return Objects.isNull(errors) ? Collections.emptyList() : errors;
    }
    /**
     * Sets list of errors.
     * @param errors the errors
     */
    public void setErrors(List<ErrorInfoDTO> errors) {
        this.errors = errors;
    }
    /**
     * Gets conflicts state.
     * @return true, if there were conflicts, false otherwise
     */
    public boolean isMergeWithConflicts() {
        return mergeWithConflicts;
    }
    /**
     * Sets conflicts state.
     * @param mergeWithConflicts true, if there were conflicts, false otherwise
     */
    public void setMergeWithConflicts(boolean mergeWithConflicts) {
        this.mergeWithConflicts = mergeWithConflicts;
    }
}
