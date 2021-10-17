
package org.datasphere.mdm.data.po;

import java.util.Date;

import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Etalon states drafts.
 */
public class EtalonRecordDraftStatePO {
    /**
     * Table name.
     */
    public static final String TABLE_NAME = "etalons_draft_states";
    /**
     * ID.
     */
    public static final String FIELD_ID = "id";
    /**
     * Etalon id.
     */
    public static final String FIELD_ETALON_ID = "etalon_id";
    /**
     * Revision number.
     */
    public static final String FIELD_REVISION = "revision";
    /**
     * Status.
     */
    public static final String FIELD_STATUS = "status";
    /**
     * Create date.
     */
    public static final String FIELD_CREATE_DATE = "create_date";
    /**
     * Created by.
     */
    public static final String FIELD_CREATED_BY = "created_by";
    /**
     * Record id.
     */
    private Integer id;
    /**
     * Etalon id.
     */
    private String etalonId;
    /**
     * Revision of the merge point of the record.
     */
    private int revision;
    /**
     * Status.
     */
    private RecordStatus status;
    /**
     * Create time stamp.
     */
    private Date createDate;
    /**
     * Created by.
     */
    private String createdBy;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the masterId
     */
    public String getEtalonId() {
        return etalonId;
    }

    /**
     * @param masterId the masterId to set
     */
    public void setEtalonId(String masterId) {
        this.etalonId = masterId;
    }

    /**
     * @return the operationId
     */
    public RecordStatus getStatus() {
        return status;
    }


    /**
     * @param status the status to set
     */
    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    /**
     * @return the revision
     */
    public int getRevision() {
        return revision;
    }

    /**
     * @param revision the revision to set
     */
    public void setRevision(int revision) {
        this.revision = revision;
    }

    /**
     * Gets create date.
     * @return date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets created by.
     * @return
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets created by.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
