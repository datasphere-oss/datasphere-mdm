

package org.datasphere.mdm.data.dto;

import java.util.List;

import org.datasphere.mdm.data.type.keys.RelationKeys;
import org.datasphere.mdm.system.type.pipeline.PipelineOutput;

/**
 * @author Mikhail Mikhailov
 * Delete relation DTO.
 */
public class DeleteRelationDTO implements RelationDTO, PipelineOutput {
    /**
     * The keys.
     */
    private RelationKeys relationKeys;
    /**
     * list of errors
     */
    private List<ErrorInfoDTO> errors;
    /**
     * Constructor.
     */
    public DeleteRelationDTO(RelationKeys relationKeys) {
        super();
        this.relationKeys = relationKeys;
    }

    /**
     * @return the relationKeys
     */
    @Override
    public RelationKeys getRelationKeys() {
        return relationKeys;
    }

    /**
     * @param relationKeys the relationKeys to set
     */
    public void setRelationKeys(RelationKeys relationKeys) {
        this.relationKeys = relationKeys;
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
}
