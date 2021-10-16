

package org.datasphere.mdm.core.po;

import java.util.Date;

/**
 * @author Mikhail Mikhailov
 * Has 'shard' field and update mark additionally.
 */
public abstract class AbstractDistributedUpdateablePO extends AbstractDistributedPO {
    /**
     * Create date.
     */
    public static final String FIELD_CREATE_DATE = "create_date";
    /**
     * Update date.
     */
    public static final String FIELD_UPDATE_DATE = "update_date";
    /**
     * Created by.
     */
    public static final String FIELD_CREATED_BY = "created_by";
    /**
     * Updated by.
     */
    public static final String FIELD_UPDATED_BY = "updated_by";
    /**
     * Create time stamp.
     */
    protected Date createDate;
    /**
     * Update time stamp.
     */
    protected Date updateDate;
    /**
     * Created by.
     */
    protected String createdBy;
    /**
     * Updated by.
     */
    protected String updatedBy;
    /**
     * Constructor.
     */
    protected AbstractDistributedUpdateablePO() {
        super();
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
     * Gets update date.
     * @return
     */
    public Date getUpdateDate() {
        return updateDate;
    }
    /**
     * Sets update date.
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
    /**
     * Gets updated by.
     * @return
     */
    public String getUpdatedBy() {
        return updatedBy;
    }
    /**
     * Sets updated by.
     * @param updatedBy
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
