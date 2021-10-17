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

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.datasphere.mdm.data.type.data.RelationType;

/**
 * @author Mikhail Mikhailov
 * Relation keys. Difference to base type:
 * type relation_type,
 * from_key record_key,
 * to_key record_key,
 * origin_keys relation_origin_key[]
 */
public class RelationKeysPO extends AbstractKeysPO {
    /**
     * Relation type.
     */
    public static final String FIELD_TYPE = "type";
    /**
     * From key.
     */
    public static final String FIELD_FROM_KEY = "from_key";
    /**
     * tO key.
     */
    public static final String FIELD_TO_KEY = "to_key";
    /**
     * Origin keys array.
     */
    public static final String FIELD_ORIGIN_KEYS = "origin_keys";
    /**
     * Relatype.
     */
    private RelationType relationType;
    /**
     * Complete from keys.
     */
    private RecordKeysPO fromKeys;
    /**
     * Complete to keys.
     */
    private RecordKeysPO toKeys;
    /**
     * Collection of origin keys.
     */
    private List<RelationOriginKeyPO> originKeys;
    /**
     * Constructor.
     */
    public RelationKeysPO() {
        super();
    }
    /**
     * @return the relationType
     */
    public RelationType getRelationType() {
        return relationType;
    }
    /**
     * @param relationType the relationType to set
     */
    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }
    /**
     * @return the fromKeys
     */
    public RecordKeysPO getFromKeys() {
        return fromKeys;
    }
    /**
     * @param fromKeys the fromKeys to set
     */
    public void setFromKeys(RecordKeysPO fromKeys) {
        this.fromKeys = fromKeys;
    }
    /**
     * @return the toKeys
     */
    public RecordKeysPO getToKeys() {
        return toKeys;
    }
    /**
     * @param toKeys the toKeys to set
     */
    public void setToKeys(RecordKeysPO toKeys) {
        this.toKeys = toKeys;
    }
    /**
     * @return the originKeys
     */
    public List<RelationOriginKeyPO> getOriginKeys() {
        return Objects.isNull(originKeys) ? Collections.emptyList() : originKeys;
    }
    /**
     * @param originKeys the originKeys to set
     */
    public void setOriginKeys(List<RelationOriginKeyPO> originKeys) {
        this.originKeys = originKeys;
    }
}
