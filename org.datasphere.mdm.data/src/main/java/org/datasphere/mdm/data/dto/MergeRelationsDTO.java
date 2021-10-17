
package org.datasphere.mdm.data.dto;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.system.dto.ExecutionResult;
import org.datasphere.mdm.system.type.pipeline.PipelineOutput;
import org.datasphere.mdm.system.type.pipeline.fragment.FragmentId;
import org.datasphere.mdm.system.type.pipeline.fragment.OutputFragment;

/**
 * @author Mikhail Mikhailov
 * Merge multiple relations state.
 */
public class MergeRelationsDTO implements RelationsDTO<MergeRelationDTO>, OutputFragment<MergeRelationsDTO>, PipelineOutput, ExecutionResult {
    /**
     * This fragment ID.
     */
    public static final FragmentId<MergeRelationsDTO> ID
        = new FragmentId<>("MERGE_RELATIONS_RESULT");
    /**
     * Operation successful, winner id is set.
     */
    private final RecordKeys winnerKeys;

    private final Map<RelationStateDTO, List<MergeRelationDTO>> relations;

    private List<ErrorInfoDTO> errors;

    private boolean mergeWithConflicts = false;

    public MergeRelationsDTO() {
        super();
        this.winnerKeys = null;
        this.relations = null;
    }
    /**
     * Constructor.
     */
    public MergeRelationsDTO(RecordKeys winnerId, Map<RelationStateDTO, List<MergeRelationDTO>> relations) {
        super();
        this.winnerKeys = winnerId;
        this.relations = relations;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public FragmentId<MergeRelationsDTO> fragmentId() {
        return ID;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<RelationStateDTO, List<MergeRelationDTO>> getRelations() {
        return Objects.isNull(relations) ? Collections.emptyMap() : relations;
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
