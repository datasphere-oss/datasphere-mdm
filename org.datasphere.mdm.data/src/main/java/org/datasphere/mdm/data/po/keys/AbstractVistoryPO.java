/*
 * Unidata Platform Community Edition
 * Copyright (c) 2013-2020, UNIDATA LLC, All rights reserved.
 * This file is part of the Unidata Platform Community Edition software.
 * 
 * Unidata Platform Community Edition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Unidata Platform Community Edition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package org.datasphere.mdm.data.po.keys;

import java.util.Date;

import org.datasphere.mdm.core.po.AbstractDistributedPO;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.data.DataShift;
import org.datasphere.mdm.core.type.data.OperationType;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Fields, common to all vistory objects.
 */
public abstract class AbstractVistoryPO extends AbstractDistributedPO {
    /**
     * ID.
     */
    public static final String FIELD_ID = "id";
    /**
     * Origin id.
     */
    public static final String FIELD_ORIGIN_ID = "origin_id";
    /**
     * Revision number.
     */
    public static final String FIELD_REVISION = "revision";
    /**
     * Valid from (validity range start time stamp).
     */
    public static final String FIELD_VALID_FROM = "valid_from";
    /**
     * Valid to (validity range end time stamp).
     */
    public static final String FIELD_VALID_TO = "valid_to";
    /**
     * Binary protostuff data.
     */
    public static final String FIELD_DATA_B = "data_b";
    /**
     * Create date.
     */
    public static final String FIELD_CREATE_DATE = "create_date";
    /**
     * Created by.
     */
    public static final String FIELD_CREATED_BY = "created_by";
    /**
     * Status of the record.
     */
    public static final String FIELD_STATUS = "status";

    /**
     * Data shift of the record.
     * May be one of {@linkplain DataState.PRISTINE} or {@linkplain DataState.REVISED}.
     */
    public static final String FIELD_SHIFT = "shift";
    /**
     * Operation type.
     */
    public static final String FIELD_OPERATION_TYPE = "operation_type";
    /**
     * Operation id.
     */
    public static final String FIELD_OPERATION_ID = "operation_id";
    /**
     * Major data API number.
     */
    public static final String FIELD_MAJOR = "major";
    /**
     * Minor data API number.
     */
    public static final String FIELD_MINOR = "minor";
    //------------------ Read only stuff begins. ------------------//
    /**
     * Read-only data section from origins - source system.
     */
    public static final String FIELD_SOURCE_SYSTEM = "source_system";
    /**
     * Read-only data section from origins - name.
     */
    public static final String FIELD_NAME = "name";
    /**
     * Status of the origin.
     */
    public static final String FIELD_ORIGIN_STATUS = "origin_status";
    /**
     * Read-only data section from origins - is enrichment.
     */
    public static final String FIELD_IS_ENRICHMENT = "enrichment";
    /**
     * Update date.
     */
    public static final String FIELD_UPDATE_DATE = "update_date";
    /**
     * Updated by.
     */
    public static final String FIELD_UPDATED_BY = "updated_by";
    /**
     * ID.
     */
    protected String id;
    /**
     * Origin id fkey.
     */
    protected String originId;
    /**
     * Revision number. Read-only.
     */
    protected int revision;
    /**
     * Valid from (validity range start time stamp).
     */
    protected Date validFrom;
    /**
     * Valid to (validity range end time stamp).
     */
    protected Date validTo;
    /**
     * Data as unmarshalled object.
     */
    protected DataRecord data;
    /**
     * JAXB raw data as string.
     */
    protected String jaxbRawData;
    /**
     * Protostuff raw data as bytes.
     */
    protected byte[] protostuffRawData;
    /**
     * Create time stamp.
     */
    protected Date createDate;
    /**
     * Created by.
     */
    protected String createdBy;
    /**
     * Status of the record.
     */
    protected RecordStatus status;

    /**
     * Data state.
     */
    protected DataShift shift;
    /**
     * OperationType
     */
    protected OperationType operationType;
    /**
     * Operation id.
     */
    protected String operationId;
    /**
     * Major number.
     */
    protected int major;
    /**
     * Minor number.
     */
    protected int minor;
    /**
     * Update time stamp.
     */
    protected Date updateDate;
    /**
     * Updated by.
     */
    protected String updatedBy;
    /**
     * Origin source system.
     */
    protected String sourceSystem;
    /**
     * Origin entity name.
     */
    protected String name;
    /**
     * Origin enrichment flag..
     */
    protected boolean enrichment;
    /**
     * Origin status mark.
     */
    protected RecordStatus originStatus;
    /**
     * Constructor.
     */
    public AbstractVistoryPO() {
        super();
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the originId
     */
    public String getOriginId() {
        return originId;
    }
    /**
     * @param originId the originId to set
     */
    public void setOriginId(String originId) {
        this.originId = originId;
    }
    /**
     * @return the revision
     */
    public int getRevision() {
        return revision;
    }
    /**
     * Sets the revision.
     * @param revision the revision to set
     */
    public void setRevision(int revision) {
        this.revision = revision;
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
     * @return the data as object
     */
    public DataRecord getData() {
        return data;
    }
    /**
     * @param data the data to set
     */
    public void setData(DataRecord data) {
        this.data = data;
    }
    /**
     * @return the jaxbRawData
     */
    public String getJaxbRawData() {
        return jaxbRawData;
    }
    /**
     * @param jaxbRawData the jaxbRawData to set
     */
    public void setJaxbRawData(String jaxbRawData) {
        this.jaxbRawData = jaxbRawData;
    }
    /**
     * @return the protostuffRawData
     */
    public byte[] getProtostuffRawData() {
        return protostuffRawData;
    }
    /**
     * @param protostuffRawData the protostuffRawData to set
     */
    public void setProtostuffRawData(byte[] protostuffRawData) {
        this.protostuffRawData = protostuffRawData;
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
    /**
     * @return the status
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
     * @return the shift
     */
    public DataShift getShift() {
        return shift;
    }
    /**
     * @param dataShift the shift to set
     */
    public void setShift(DataShift dataState) {
        this.shift = dataState;
    }
    /**
     * @return  OperationType
     */
    public OperationType getOperationType() {
        return operationType;
    }
    /**
     * @param OperationType
     */
    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
    /**
     * @return the operationId
     */
    public String getOperationId() {
        return operationId;
    }
    /**
     * @param operationId the operationId to set
     */
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
    /**
     * @return the major
     */
    public int getMajor() {
        return major;
    }
    /**
     * @param major the major to set
     */
    public void setMajor(int major) {
        this.major = major;
    }
    /**
     * @return the minor
     */
    public int getMinor() {
        return minor;
    }
    /**
     * @param minor the minor to set
     */
    public void setMinor(int minor) {
        this.minor = minor;
    }
    /**
     * @return the sourceSystem
     */
    public String getSourceSystem() {
        return sourceSystem;
    }
    /**
     * @param sourceSystem the sourceSystem to set
     */
    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the enrichment
     */
    public boolean isEnrichment() {
        return enrichment;
    }
    /**
     * @param enrichment the enrichment to set
     */
    public void setEnrichment(boolean enrichment) {
        this.enrichment = enrichment;
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
    /**
     * @return the originStatus
     */
    public RecordStatus getOriginStatus() {
        return originStatus;
    }
    /**
     * @param originStatus the originStatus to set
     */
    public void setOriginStatus(RecordStatus originStatus) {
        this.originStatus = originStatus;
    }
}
