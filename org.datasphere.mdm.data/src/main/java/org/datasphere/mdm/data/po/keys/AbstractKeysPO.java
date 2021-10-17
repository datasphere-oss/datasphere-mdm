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

import org.datasphere.mdm.core.po.AbstractObjectPO;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Base class for keys.
 * Common part:
 * shard integer,
 * lsn bigint,
 * id uuid,
 * name varchar(256),
 * status record_status,
 * state approval_state,
 * approved boolean,
 * create_date timestamptz,
 * created_by varchar(256),
 * update_date timestamptz,
 * updated_by varchar(256),
 */
public class AbstractKeysPO extends AbstractObjectPO {
    /**
     * Etalon ID.
     */
    public static final String FIELD_ID = "id";
    /**
     * Etalon status {@link RecordStatus}.
     */
    public static final String FIELD_STATUS = "status";

    /**
     * Etalon LSN.
     */
    public static final String FIELD_LSN = "lsn";
    /**
     * Etalon name.
     */
    public static final String FIELD_NAME = "name";

    /**
     * Record's shard.
     */
    public static final String FIELD_SHARD = "shard";
    /**
     * The shard where the record reside.
     */
    private int shard;
    /**
     * Etalon local sequence number (unique within a shard).
     */
    private long lsn;
    /**
     * Record etalon id.
     */
    private String id;
    /**
     * Type name as set by entity definition.
     */
    private String name;
    /**
     * Etalon status of the record.
     */
    private RecordStatus status;

    /**
     * Record was already published.
     */
    private Boolean approved;

    /**
     * Constructor.
     */
    public AbstractKeysPO() {
        super();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param etalonId the id to set
     */
    public void setId(String etalonId) {
        this.id = etalonId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param etalonName the name to set
     */
    public void setName(String etalonName) {
        this.name = etalonName;
    }

    /**
     * @return the status
     */
    public RecordStatus getStatus() {
        return status;
    }

    /**
     * @param etalonStatus the status to set
     */
    public void setStatus(RecordStatus etalonStatus) {
        this.status = etalonStatus;
    }

    /**
     * @return the isPublished
     */
    public Boolean isApproved() {
        return approved;
    }

    /**
     * @param hasApprovedRevisions the isPublished to set
     */
    public void setApproved(Boolean hasApprovedRevisions) {
        this.approved = hasApprovedRevisions;
    }

    /**
     * @return the lsn
     */
    public long getLsn() {
        return lsn;
    }

    /**
     * @param etalonGsn the lsn to set
     */
    public void setLsn(long etalonGsn) {
        this.lsn = etalonGsn;
    }

    /**
     * @return the shard
     */
    public int getShard() {
        return shard;
    }

    /**
     * @param shard the shard to set
     */
    public void setShard(int shard) {
        this.shard = shard;
    }

}
