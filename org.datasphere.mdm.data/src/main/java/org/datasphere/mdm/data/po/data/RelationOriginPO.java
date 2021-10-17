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

import java.util.UUID;

import org.datasphere.mdm.core.po.AbstractDistributedUpdateablePO;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Relation origin record.
 */
public class RelationOriginPO extends AbstractDistributedUpdateablePO {
    /**
     * Table name.
     */
    public static final String TABLE_NAME = "relation_origins";
    /**
     * Id.
     */
    public static final String FIELD_ID = "id";
    /**
     * Initial owner ID.
     */
    public static final String FIELD_INITIAL_OWNER = "initial_owner";
    /**
     * Etalon ID.
     */
    public static final String FIELD_ETALON_ID = "etalon_id";
    /**
     * Relation name.
     */
    public static final String FIELD_NAME = "name";
    /**
     * Origin id from.
     */
    public static final String FIELD_ORIGIN_ID_FROM = "origin_id_from";
    /**
     * Origin ID to.
     */
    public static final String FIELD_ORIGIN_ID_TO = "origin_id_to";
    /**
     * Source system.
     */
    public static final String FIELD_SOURCE_SYSTEM = "source_system";
    /**
     * Status.
     */
    public static final String FIELD_STATUS = "status";
    /**
     * Tells, if this origin is an enrichment one or not.
     */
    public static final String FIELD_ENRICHMENT = "enrichment";
    /**
     * Record id.
     */
    private String id;
    /**
     * Initial owner id.
     */
    private UUID initialOwner;
    /**
     * Etalon ID.
     */
    private String etalonId;
    /**
     * Relation name.
     */
    private String name;
    /**
     * Origin id from.
     */
    private String fromOriginId;
    /**
     * Origin ID to.
     */
    private String toOriginId;
    /**
     * Source system.
     */
    private String sourceSystem;
    /**
     * Tells, if this origin is an enrichment one or not.
     */
    private boolean enrichment;
    /**
     * Status.
     */
    private RecordStatus status;
    /**
     * Constructor.
     */
    public RelationOriginPO() {
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
     * @return the etalonId
     */
    public String getEtalonId() {
        return etalonId;
    }

    /**
     * @param etalonId the etalonId to set
     */
    public void setEtalonId(String etalonId) {
        this.etalonId = etalonId;
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
     * @return the fromOriginId
     */
    public String getFromOriginId() {
        return fromOriginId;
    }

    /**
     * @param fromOriginId the fromOriginId to set
     */
    public void setFromOriginId(String originIdFrom) {
        this.fromOriginId = originIdFrom;
    }


    /**
     * @return the toOriginId
     */
    public String getToOriginId() {
        return toOriginId;
    }

    /**
     * @param toOriginId the toOriginId to set
     */
    public void setToOriginId(String originIdTo) {
        this.toOriginId = originIdTo;
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
}
