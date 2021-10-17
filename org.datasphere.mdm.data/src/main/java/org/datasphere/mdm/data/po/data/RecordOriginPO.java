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

import org.datasphere.mdm.data.util.StorageUtils;
import org.datasphere.mdm.core.po.AbstractDistributedUpdateablePO;
import org.datasphere.mdm.core.type.data.RecordStatus;
import org.datasphere.mdm.core.type.keys.ExternalId;

/**
 * @author Mikhail Mikhailov
 * Origin record persistent object.
 */
public class RecordOriginPO extends AbstractDistributedUpdateablePO {
    /**
     * Table name.
     */
    public static final String TABLE_NAME = "record_origins";
    /**
     * ID.
     */
    public static final String FIELD_ID = "id";
    /**
     * Initial owner ID.
     */
    public static final String FIELD_INITIAL_OWNER = "initial_owner";
    /**
     * Name (virtual column, actually persisted in 'etalons' table).
     */
    public static final String FIELD_NAME = "name";
    /**
     * Origin name.
     */
    public static final String FIELD_SOURCE_SYSTEM = "source_system";
    /**
     * External ID (foreign primary key ID).
     */
    public static final String FIELD_EXTERNAL_ID = "external_id";
    /**
     * Etalon (golden record) id. FK.
     */
    public static final String FIELD_ETALON_ID = "etalon_id";
    /**
     * Tells, if this origin is an enrichment one or not.
     */
    public static final String FIELD_ENRICHMENT = "enrichment";
    /**
     * Status.
     */
    public static final String FIELD_STATUS = "status";
    /**
     * Record id.
     */
    private String id;
    /**
     * Initial owner id.
     */
    private UUID initialOwner;
    /**
     * Type name as set by entity definition (virtual column, actually persisted in 'etalons' table).
     */
    private String name;
    /**
     * Name of the source system.
     */
    private String sourceSystem;
    /**
     * Natural key (external ID) of the record, not necessary unique.
     */
    private String externalId;
    /**
     * The record_external_keys shard number.
     */
    private int externalIdShard;
    /**
     * Compact representation of the externalId.
     */
    private String externalIdCompact;
    /**
     * Id of the golden record (Etalon).
     */
    private String etalonId;
    /**
     * Tells, if this origin is an enrichment one or not.
     */
    private boolean enrichment;
    /**
     * Status of the record.
     */
    private RecordStatus status;
    /**
     * Constructor.
     */
    public RecordOriginPO() {
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the sourceSystem
     */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * @return the externalIdShard
     */
    public int getExternalIdShard() {
        return externalIdShard;
    }

    /**
     * @return the externalIdCompact
     */
    public String getExternalIdCompact() {
        return externalIdCompact;
    }

    /**
     * @return the externalId
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * @param naturalKey the externalId to set
     * @param entityName the entity name to set
     * @param sourceSystem the source system to set
     */
    public void setExternalId(String naturalKey, String entityName, String sourceSystem) {
        this.externalId = naturalKey;
        this.name = entityName;
        this.sourceSystem = sourceSystem;
        this.externalIdCompact = ExternalId.compact(this.externalId, this.name, this.sourceSystem);
        this.externalIdShard = StorageUtils.shard(this.externalIdCompact);
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
    public void setEtalonId(String goldenId) {
        this.etalonId = goldenId;
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

}
