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

import java.util.Objects;

/**
 * @author Mikhail Mikhailov
 * Record origin key java counterpart.
 */
public class RecordOriginKeyPO extends AbstractOriginKeyPO {
    /**
     * Origin external id.
     */
    public static final String FIELD_EXTERNAL_ID = "external_id";
    /**
     * Origin external id.
     */
    protected String externalId;
    /**
     * Constructor.
     */
    public RecordOriginKeyPO() {
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
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.externalId, this.sourceSystem);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RecordOriginKeyPO)) {
            return false;
        }

        RecordOriginKeyPO other = (RecordOriginKeyPO) obj;
        return Objects.equals(this.id, other.id)
            && Objects.equals(this.externalId, other.externalId)
            && Objects.equals(this.sourceSystem, other.sourceSystem);
    }


}
