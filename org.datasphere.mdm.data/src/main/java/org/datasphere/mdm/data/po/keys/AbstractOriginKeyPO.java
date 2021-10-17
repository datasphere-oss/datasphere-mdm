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

import java.util.UUID;

import org.datasphere.mdm.core.po.AbstractObjectPO;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Common fields, all types of origin keys share:
 * id uuid,
 * status record_status,
 * enrichment boolean,
 * revision integer,
 * source_system varchar(256),
 */
public abstract class AbstractOriginKeyPO extends AbstractObjectPO {
    /**
     * Origin ID.
     */
    public static final String FIELD_ID = "id";
    /**
     * Initial owner ID.
     */
    public static final String FIELD_INITIAL_OWNER = "initial_owner";
    /**
     * Origin status.
     */
    public static final String FIELD_STATUS = "status";
    /**
     * Enrichment.
     */
    public static final String FIELD_ENRICHMENT = "enrichment";
    /**
     * Revision.
     */
    public static final String FIELD_REVISION = "revision";
    /**
     * Source system.
     */
    public static final String FIELD_SOURCE_SYSTEM = "source_system";
    /**
     * Origin ID.
     */
    protected UUID id;
    /**
     * Initial owner ID.
     */
    protected UUID initialOwner;
    /**
     * Origin status.
     */
    protected RecordStatus status;
    /**
     * Enrichment.
     */
    protected boolean enrichment;
    /**
     * Revision.
     */
    protected int revision;
    /**
     * Source system.
     */
    protected String sourceSystem;
    /**
     * Constructor.
     */
    protected AbstractOriginKeyPO() {
        super();
    }
    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(UUID id) {
        this.id = id;
    }
    /**
     * @return the initialOwner
     */
    public UUID getInitialOwner() {
        return initialOwner;
    }
    /**
     * @param initialOwner the initialOwner to set
     */
    public void setInitialOwner(UUID initialOwner) {
        this.initialOwner = initialOwner;
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

}
