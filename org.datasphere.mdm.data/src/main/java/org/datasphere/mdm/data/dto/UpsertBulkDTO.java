

/**
 *
 */
package org.datasphere.mdm.data.dto;

import java.util.List;

/**
 * @author Mikhail Mikhailov
 * Bulk Upsert result.
 */
public class UpsertBulkDTO {

    /**
     * Processed records.
     */
    private List<UpsertRecordDTO> records;

    /**
     * Constructor.
     */
    public UpsertBulkDTO() {
        super();
    }

    /**
     * @return the records
     */
    public List<UpsertRecordDTO> getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(List<UpsertRecordDTO> records) {
        this.records = records;
    }

}
