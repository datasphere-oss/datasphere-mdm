
/**
 *
 */
package org.datasphere.mdm.data.dto;

import org.datasphere.mdm.data.type.data.EtalonRelation;
import org.datasphere.mdm.data.type.keys.RelationKeys;
import org.datasphere.mdm.core.dto.ResourceSpecificRightDTO;
import org.datasphere.mdm.system.type.pipeline.PipelineOutput;

/**
 * @author Mikhail Mikhailov
 * Single relation version DTO.
 */
public class GetRelationDTO implements RelationDTO, EtalonRelationDTO, PipelineOutput {
    /**
     * The keys.
     */
    private RelationKeys relationKeys;
    /**
     * Relation object, either
     */
    private EtalonRelation etalon;

    private Boolean periodActive;

    /**
     * Rights.
     */
    private ResourceSpecificRightDTO rights;
    /**
     * Constructor.
     */
    public GetRelationDTO(RelationKeys relationKeys) {
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
     * Gets the relation object.
     * @return relation object
     */
    @Override
    public EtalonRelation getEtalon() {
        return etalon;
    }
    /**
     * @param etalon the etalon to set
     */
    public void setEtalon(EtalonRelation etalon) {
        this.etalon = etalon;
    }
    /**
     * @return the rights
     */
    public ResourceSpecificRightDTO getRights() {
        return rights;
    }
    /**
     * @param rights the rights to set
     */
    public void setRights(ResourceSpecificRightDTO rights) {
        this.rights = rights;
    }


    public Boolean getPeriodActive() {
        return periodActive;
    }

    public void setPeriodActive(Boolean periodActive) {
        this.periodActive = periodActive;
    }
}
