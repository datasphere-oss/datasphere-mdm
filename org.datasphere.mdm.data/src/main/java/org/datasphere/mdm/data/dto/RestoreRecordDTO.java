

package org.datasphere.mdm.data.dto;

import java.util.Collection;
import java.util.List;

import org.datasphere.mdm.data.type.data.EtalonRecord;
import org.datasphere.mdm.data.type.keys.RecordKeys;
import org.datasphere.mdm.core.dto.ResourceSpecificRightDTO;
import org.datasphere.mdm.system.dto.AbstractCompositeResult;
import org.datasphere.mdm.system.dto.ExecutionResult;
import org.datasphere.mdm.system.type.pipeline.PipelineOutput;

/**
 * @author Mikhail Mikhailov
 * Restore result DTO.
 */
public class RestoreRecordDTO extends AbstractCompositeResult implements RecordDTO, EtalonRecordDTO, PipelineOutput, ExecutionResult {
    /**
     * Record keys for short upsert.
     */
    private RecordKeys recordKeys;
    /**
     * Etalon record for this period or null.
     */
    private EtalonRecord etalon;
    /**
     * All periods.
     */
    private Collection<EtalonRecord> periods;
    /**
     * list of errors
     */
    private List<ErrorInfoDTO> errors;
    /**
     * Rights.
     */
    private ResourceSpecificRightDTO rights;
    /**
     * Constructor.
     * @param type action performed
     */
    public RestoreRecordDTO() {
        super();
    }
    /**
     * @return the golden record
     */
    @Override
    public EtalonRecord getEtalon() {
        return etalon;
    }
    /**
     * @param etalon the golden to set
     */
    public void setEtalon(EtalonRecord etalon) {
        this.etalon = etalon;
    }
    /**
     * @return the periods
     */
    public Collection<EtalonRecord> getPeriods() {
        return periods;
    }
    /**
     * @param periods the periods to set
     */
    public void setPeriods(Collection<EtalonRecord> periods) {
        this.periods = periods;
    }
    /**
     * @return the keys
     */
    @Override
    public RecordKeys getRecordKeys() {
        return recordKeys;
    }
    /**
     * @param keys the keys to set
     */
    public void setRecordKeys(RecordKeys keys) {
        this.recordKeys = keys;
    }

    public List<ErrorInfoDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorInfoDTO> errors) {
        this.errors = errors;
    }

    public ResourceSpecificRightDTO getRights() {
        return rights;
    }

    public void setRights(ResourceSpecificRightDTO rights) {
        this.rights = rights;
    }
}
