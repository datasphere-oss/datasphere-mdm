
package org.datasphere.mdm.data.dto;

import java.util.List;
import java.util.Map;

/**
 * @author Dmitry Kopin on 22.05.2018.
 */
public class SplitRecordsDTO {

    /**
     * list of errors
     */
    private List<ErrorInfoDTO> errors;

    private Map<String, String> etalonId;

    /**
     * list of errors
     */
    public List<ErrorInfoDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorInfoDTO> errors) {
        this.errors = errors;
    }

    public Map<String, String> getEtalonId() {
        return etalonId;
    }

    public void setEtalonId(Map<String, String> etalonId) {
        this.etalonId = etalonId;
    }
}
