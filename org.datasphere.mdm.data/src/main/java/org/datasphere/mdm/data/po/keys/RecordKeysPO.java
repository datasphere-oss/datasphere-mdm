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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.data.type.keys.RecordModificationBoxKey;

/**
 * @author Mikhail Mikhailov
 * PO for keys digest.
 * The DB type is:
 *
 * create type record_key as (
 * id uuid,
 * status record_status,
 * state approval_state,
 * lsn bigint,
 * name varchar(256),
 * approved boolean,
 * create_date timestamptz,
 * created_by varchar(256),
 * update_date timestamptz,
 * updated_by varchar(256),
 * origin_keys record_origin_key[]);
 */
public class RecordKeysPO extends AbstractKeysPO {
    /**
     * Origin keys.
     */
    public static final String FIELD_ORIGIN_KEYS = "origin_keys";
    /**
     * Collection of origin keys.
     */
    private Map<String, RecordOriginKeyPO> originKeys;
    /**
     * Constructor.
     */
    public RecordKeysPO() {
        super();
    }
    /**
     * @return the originKeys
     */
    public List<RecordOriginKeyPO> getOriginKeys() {
        return Objects.isNull(originKeys) ? Collections.emptyList() : new ArrayList<>(originKeys.values());
    }
    /**
     * @param originKeys the originKeys to set
     */
    public void setOriginKeys(List<RecordOriginKeyPO> originKeys) {
        if (CollectionUtils.isNotEmpty(originKeys)) {
            this.originKeys = originKeys.stream()
                    .collect(Collectors.toMap(
                            ok -> RecordModificationBoxKey.toRecordBoxKey(ok.getSourceSystem(), ok.getExternalId()),
                            Function.identity()));
        }
    }

    public RecordOriginKeyPO findByBoxKey(String boxKey) {
        return originKeys.get(boxKey);
    }

    public RecordOriginKeyPO findByExternalId(String externalId, String sourceSystem) {
        return originKeys.get(RecordModificationBoxKey.toRecordBoxKey(sourceSystem, externalId));
    }
 }
