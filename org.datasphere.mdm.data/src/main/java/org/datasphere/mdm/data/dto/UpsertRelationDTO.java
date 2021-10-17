

/**
 *
 */
package org.datasphere.mdm.data.dto;

import java.util.Date;
import java.util.List;

import org.datasphere.mdm.data.type.data.EtalonRelation;
import org.datasphere.mdm.data.type.data.UpsertAction;
import org.datasphere.mdm.data.type.keys.RelationKeys;
import org.datasphere.mdm.core.dto.ResourceSpecificRightDTO;
import org.datasphere.mdm.core.type.data.RecordStatus;
import org.datasphere.mdm.system.type.pipeline.PipelineOutput;

/**
 * @author Mikhail Mikhailov
 * Upsert relation DTO.
 */
public class UpsertRelationDTO implements RelationDTO, EtalonRelationDTO, PipelineOutput {
    /**
     * Relation keys.
     */
    private RelationKeys relationKeys;
    /**
     * The relation itself.
     */
    private EtalonRelation etalon;
    /**
     * Period from date.
     */
    private Date validFrom;
    /**
     * Period to date.
     */
    private Date validTo;
    /**
     * Rights.
     */
    private ResourceSpecificRightDTO rights;
    /**
     * Actual action.
     */
    private UpsertAction action;
    /**
     * list of errors
     */
    private List<ErrorInfoDTO> errors;
    /**
     * The draft id.
     */
    private Long draftId;
    /**
     * Constructor.
     */
    public UpsertRelationDTO() {
        super();
    }
    /**
     * Constructor.
     */
    public UpsertRelationDTO(RelationKeys relationKeys) {
        super();
        this.relationKeys = relationKeys;
    }

    /**
     * @return the etalonKey
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
     * @return the relation
     */
    @Override
    public EtalonRelation getEtalon() {
        return etalon;
    }

    /**
     * @param relation the relation to set
     */
    public void setEtalon(EtalonRelation relation) {
        this.etalon = relation;
    }

    /**
     * @return the etalonStatus
     */
    public RecordStatus getEtalonStatus() {
        return relationKeys != null ? relationKeys.getEtalonKey().getStatus() : null;
    }

    /**
     * @return the originStatus
     */
    public RecordStatus getOriginStatus() {
        return relationKeys != null ? relationKeys.getOriginKey().getStatus() : null;
    }

    /**
     * @return the validFrom
     */
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * @param validFrom the validFrom to set
     */
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * @return the validTo
     */
    public Date getValidTo() {
        return validTo;
    }

    /**
     * @param validTo the validTo to set
     */
    public void setValidTo(Date validTo) {
        this.validTo = validTo;
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

    /**
     * @return the action
     */
    public UpsertAction getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(UpsertAction action) {
        this.action = action;
    }


    public List<ErrorInfoDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorInfoDTO> errors) {
        this.errors = errors;
    }
    /**
     * @return the draftId
     */
    public Long getDraftId() {
        return draftId;
    }
    /**
     * @param draftId the draftId to set
     */
    public void setDraftId(Long draftId) {
        this.draftId = draftId;
    }

}
