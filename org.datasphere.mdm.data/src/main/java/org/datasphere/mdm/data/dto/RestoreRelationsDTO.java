

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
 */
public class RestoreRelationsDTO implements RelationsDTO<RestoreRelationDTO>, OutputFragment<RestoreRelationsDTO>, PipelineOutput, ExecutionResult {
    /**
     * This fragment ID.
     */
    public static final FragmentId<RestoreRelationsDTO> ID
        = new FragmentId<>("RESTORE_RELATIONS_RESULT");
    /**
     * Relations upsert result.
     */
    private final Map<RelationStateDTO, List<RestoreRelationDTO>> relations;
    /**
     * Constructor.
     */
    public RestoreRelationsDTO(Map<RelationStateDTO, List<RestoreRelationDTO>> relations) {
        super();
        this.relations = relations;
    }
    /**
     * Constructor.
     */
    public RestoreRelationsDTO() {
        super();
        this.relations = new HashMap<>();
    }
    /**
     * @return the relations
     */
    @Override
    public Map<RelationStateDTO, List<RestoreRelationDTO>> getRelations() {
        return Objects.isNull(relations) ? Collections.emptyMap() : relations;
    }

    @Override
    public FragmentId<RestoreRelationsDTO> fragmentId() {
        return ID;
    }
}
