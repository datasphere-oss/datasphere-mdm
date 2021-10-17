

/**
 *
 */
package org.datasphere.mdm.data.dto;

import org.datasphere.mdm.data.type.data.EtalonRecord;

/**
 * @author Mikhail Mikhailov
 * Etalon record getter interface.
 */
public interface EtalonRecordDTO {
    /**
     * @return the golden record
     */
    public EtalonRecord getEtalon();
}
