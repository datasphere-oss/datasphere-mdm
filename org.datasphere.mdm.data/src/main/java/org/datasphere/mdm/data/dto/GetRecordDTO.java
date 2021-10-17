
package org.datasphere.mdm.data.dto;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.datasphere.mdm.data.type.data.EtalonRecord;
import org.datasphere.mdm.data.type.data.OriginRecord;
import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.core.dto.ResourceSpecificRightDTO;
import org.datasphere.mdm.core.type.data.impl.SimpleAttributesDiff;
import org.datasphere.mdm.system.dto.AbstractCompositeResult;
import org.datasphere.mdm.system.dto.ExecutionResult;
import org.datasphere.mdm.system.type.pipeline.PipelineOutput;

/**
 * @author Mikhail Mikhailov
 *         Get record result DTO.
 */
public class GetRecordDTO extends AbstractCompositeResult implements RecordDTO, OriginRecordsDTO, EtalonRecordDTO, PipelineOutput, ExecutionResult {
    /**
     * Record keys.
     */
    private RecordKeys recordKeys;
    /**
     * Rights.
     */
    private ResourceSpecificRightDTO rights;
    /**
     * Minimum lower bound.
     */
    private Date rangeFromMax;
    /**
     * Maximum upper bound.
     */
    private Date rangeToMin;
    /**
     * Golden record.
     */
    private EtalonRecord etalon;
    /**
     * 0 or more origin records.
     */
    private List<OriginRecord> origins;
    /**
     * Accessory map
     */
    private Map<String, String> attributeWinnerMap;
    /**
     * Version field.
     */
    private int version;
    /**
     * Diff to draft.
     */
    private SimpleAttributesDiff diffToDraft;
    /**
     * Diff to previous state.
     */
    private SimpleAttributesDiff diffToPrevious;
    /**
     * Constructor.
     */
    public GetRecordDTO() {
        super();
    }
    /**
     * Constructor.
     * @param keys the keys
     */
    public GetRecordDTO(RecordKeys keys) {
        super();
        this.recordKeys = keys;
    }
    /**
     * @return the recordKeys
     */
    @Override
    public RecordKeys getRecordKeys() {
        return recordKeys;
    }

    /**
     * @param recordKeys the recordKeys to set
     */
    public void setRecordKeys(RecordKeys recordKeys) {
        this.recordKeys = recordKeys;
    }

    /**
     * @return the rangeFromMax
     */
    public Date getRangeFromMax() {
        return rangeFromMax;
    }

    /**
     * @param rangeFromMax the rangeFromMax to set
     */
    public void setRangeFromMax(Date rangeFromMax) {
        this.rangeFromMax = rangeFromMax;
    }

    /**
     * @return the rangeToMin
     */
    public Date getRangeToMin() {
        return rangeToMin;
    }

    /**
     * @param rangeFromMin the rangeToMin to set
     */
    public void setRangeToMin(Date rangeFromMin) {
        this.rangeToMin = rangeFromMin;
    }

    /**
     * @return the goldenRecord
     */
    @Override
    public EtalonRecord getEtalon() {
        return etalon;
    }

    /**
     * @param goldenRecord the goldenRecord to set
     */
    public void setEtalon(EtalonRecord goldenRecord) {
        this.etalon = goldenRecord;
    }

    /**
     * @return the origins
     */
    @Override
    public List<OriginRecord> getOrigins() {
        return origins == null ? Collections.emptyList() : origins;
    }

    /**
     * @param originRecords the origins to set
     */
    public void setOrigins(List<OriginRecord> originRecords) {
        this.origins = originRecords;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * @return
     */
    public Map<String, String> getAttributeWinnersMap() {
        return attributeWinnerMap;
    }

    /**
     * @param attributeWinnerMap
     */
    public void setAttributeWinnerMap(Map<String, String> attributeWinnerMap) {
        this.attributeWinnerMap = attributeWinnerMap;
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
     * @return the diffToDraft
     */
    public SimpleAttributesDiff getDiffToDraft() {
        return diffToDraft;
    }
    /**
     * @param diffToDraft the diffToDraft to set
     */
    public void setDiffToDraft(SimpleAttributesDiff diffToDraft) {
        this.diffToDraft = diffToDraft;
    }
    /**
     * @return the diffToPrevious
     */
    public SimpleAttributesDiff getDiffToPrevious() {
        return diffToPrevious;
    }
    /**
     * @param diffToPrevious the diffToPrevious to set
     */
    public void setDiffToPrevious(SimpleAttributesDiff diffToPrevious) {
        this.diffToPrevious = diffToPrevious;
    }
}
