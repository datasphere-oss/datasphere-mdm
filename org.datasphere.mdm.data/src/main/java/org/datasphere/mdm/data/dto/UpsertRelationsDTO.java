

/**
 *
 */
package org.datasphere.mdm.data.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.datasphere.mdm.system.dto.ExecutionResult;
import org.datasphere.mdm.system.type.pipeline.PipelineOutput;
import org.datasphere.mdm.system.type.pipeline.fragment.FragmentId;
import org.datasphere.mdm.system.type.pipeline.fragment.OutputFragment;

/**
 * @author Mikhail Mikhailov
 *
 */
public class UpsertRelationsDTO implements RelationsDTO<UpsertRelationDTO>, OutputFragment<UpsertRelationsDTO>, PipelineOutput, ExecutionResult {
    /**
     * This fragment ID.
     */
    public static final FragmentId<UpsertRelationsDTO> ID
        = new FragmentId<>("UPSERT_RELATIONS_RESULT");
    /**
     * Relations upsert result.
     */
    private final Map<RelationStateDTO, List<UpsertRelationDTO>> relations;
    /**
     * Constructor.
     */
    public UpsertRelationsDTO(Map<RelationStateDTO, List<UpsertRelationDTO>> relations) {
        super();
        this.relations = relations;
    }
    /**
     * Constructor.
     */
    public UpsertRelationsDTO() {
        super();
        this.relations = new HashMap<>();
    }
    /**
     * @return the relations
     */
    @Override
    public Map<RelationStateDTO, List<UpsertRelationDTO>> getRelations() {
        return Objects.isNull(relations) ? Collections.emptyMap() : relations;
    }

    @Override
    public FragmentId<UpsertRelationsDTO> fragmentId() {
        return ID;
    }
}
