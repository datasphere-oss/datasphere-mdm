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

/**
 *
 */
package org.datasphere.mdm.data.po.data;

import org.datasphere.mdm.data.po.keys.AbstractVistoryPO;

/**
 * @author Mikhail Mikhailov
 * Immutable vistory (versions + history) records table.
 */
public class RecordVistoryPO extends AbstractVistoryPO {
    /**
     * Table name.
     */
    public static final String TABLE_NAME = "record_vistory";
    /**
     * Read-only data section from origins - external id.
     */
    public static final String FIELD_EXTERNAL_ID = "external_id";
    /**
     * Origin external id.
     */
    private String externalId;
    /**
     * Constructor.
     */
    public RecordVistoryPO() {
        super();
    }
    /**
     * @return the externalId
     */
    public String getExternalId() {
        return externalId;
    }
    /**
     * @param externalId the externalId to set
     */
    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return
            String.format(new StringBuilder()
                .append(TABLE_NAME)
                .append("%n")
                .append(FIELD_ID)
                .append(": %s%n")
                .append(FIELD_ORIGIN_ID)
                .append(": %s%n")
                .append(FIELD_REVISION)
                .append(": %d%n")
                .append(FIELD_VALID_FROM)
                .append(": %tc%n")
                .append(FIELD_VALID_TO)
                .append(": %tc%n")
                .append(FIELD_CREATE_DATE)
                .append(": %tc%n")
                .append(FIELD_CREATED_BY)
                .append(": %s%n")
                .append(FIELD_STATUS)
                .append(": %s%n")
                .append(FIELD_SHIFT)
                .append(": %s%n")
                .toString(),
                id,
                originId,
                revision,
                validFrom,
                validTo,
                data == null ? "null" : "[record]",
                createDate,
                createdBy,
                status,
                shift);
    }

}
