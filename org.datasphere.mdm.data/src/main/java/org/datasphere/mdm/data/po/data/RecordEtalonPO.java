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

package org.datasphere.mdm.data.po.data;

import org.datasphere.mdm.core.po.AbstractDistributedUpdateablePO;
import org.datasphere.mdm.core.type.data.RecordStatus;


/**
 * @author Mikhail Mikhailov
 * Etalon record persistent object.
 */
public class RecordEtalonPO extends AbstractDistributedUpdateablePO {
    /**
     * Table name.
     */
    public static final String TABLE_NAME = "record_etalons";
    /**
     * ID.
     */
    public static final String FIELD_ID = "id";
    /**
     * Name.
     */
    public static final String FIELD_NAME = "name";
    /**
     * Status.
     */
    public static final String FIELD_STATUS = "status";

    /**
     * Forward sequence number.
     */
    public static final String FIELD_LSN = "lsn";
    /**
     * Operation ID.
     */
    public static final String FIELD_OPERATION_ID = "operation_id";
    /**
     * Record id.
     */
    private String id;
    /**
     * Type name as set by entity definition.
     */
    private String name;
    /**
     * Status of the record.
     */
    private RecordStatus status;

    /**
     * Global sequence number.
     */
    private long lsn;
    /**
     * The operation id.
     */
    private String operationId;
    /**
     * Constructor.
     */
    public RecordEtalonPO() {
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
     * @return the lsn
     */
    public long getLsn() {
        return lsn;
    }

    /**
     * @param lsn the gsn to set
     */
    public void setLsn(long lsn) {
        this.lsn = lsn;
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

}
