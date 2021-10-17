

/**
 *
 */
package org.datasphere.mdm.data.dto;

import org.datasphere.mdm.data.type.data.RelationType;

/**
 * @author Mikhail Mikhailov
 * Relation state DTO.
 */
public class RelationStateDTO {

    /**
     * Relation name.
     */
    private String relationName;
    /**
     * Relation type.
     */
    private RelationType relationType;
    /**
     * Constructor.
     */
    public RelationStateDTO() {
        super();
    }
    /**
     * Constructor.
     */
    public RelationStateDTO(String relationName, RelationType relationType) {
        super();
        this.relationName = relationName;
        this.relationType = relationType;
    }
    /**
     * @return the relationName
     */
    public String getRelationName() {
        return relationName;
    }
    /**
     * @param relationName the relationName to set
     */
    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }
    /**
     * @return the relationType
     */
    public RelationType getRelationType() {
        return relationType;
    }
    /**
     * @param relationType the relationType to set
     */
    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }
}
