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
package org.datasphere.mdm.data.dao.rm;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.datasphere.mdm.data.dao.rm.tokenizer.RecordTimelineRowTokenizer;
import org.datasphere.mdm.data.dao.rm.tokenizer.RecordVistoryRowTokenizer;
import org.datasphere.mdm.data.po.data.RecordTimelinePO;
import org.datasphere.mdm.data.po.data.RecordVistoryPO;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mikhail Mikhailov
 * Origins vistory row mapper.
 */
public class RecordTimelineRowMapper implements RowMapper<RecordTimelinePO> {
    /**
     * Ref field name.
     */
    private static final String ETALON_ID_REF = "__etalon_id";
    /**
     * Default row mapper.
     */
    public static final RecordTimelineRowMapper DEFAULT_RECORD_TIMELINE_ROW_MAPPER = new RecordTimelineRowMapper();
    /**
     * Multiple relations by from etalon id result set extractor.
     */
    public static final ResultSetExtractor<Map<String, RecordTimelinePO>>
        DEFAULT_RECORD_TIMELINES_EXTRACTOR
        = rs -> {

            Map<String, RecordTimelinePO> result = new HashMap<>();
            int rowNum = 0;
            while (rs.next()) {
                RecordTimelinePO po = DEFAULT_RECORD_TIMELINE_ROW_MAPPER.mapRow(rs, ++rowNum);
                String etalonIdRef = rs.getString(ETALON_ID_REF);
                result.put(etalonIdRef, po);
            }

            return result;
        };

    /**
     * Constructor.
     */
    private RecordTimelineRowMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordTimelinePO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RecordTimelineRowTokenizer.DEFAULT_RECORD_TIMELINE_TOKENIZER.process((PGobject) rs.getObject(1));
        /*
        RecordTimelinePO po = new RecordTimelinePO();

        // 1. Keys
        Object pgKeys = rs.getObject(RecordTimelinePO.FIELD_KEYS);
        if (!rs.wasNull()) {
            RecordKeysPO keys = RecordKeysRowTokenizer.DEFAULT_RECORD_KEYS_TOKENIZER.process((PGobject) pgKeys);
            po.setKeys(keys);
        }

        // 2. Vistory array
        Array vistory = rs.getArray(RecordTimelinePO.FIELD_VISTORY_DATA);
        po.setVistory(getVistory(vistory));

        return po;
        */
    }
    /**
     * Parse vistory objects.
     * @param origins
     * @return
     * @throws SQLException
     */
    private List<RecordVistoryPO> getVistory(Array origins) throws SQLException {

        if (Objects.isNull(origins)) {
            return Collections.emptyList();
        }

        try {

            Object[] lines = (Object[]) origins.getArray();
            List<RecordVistoryPO> result = new ArrayList<>(lines.length);
            for (int i = 0; i < lines.length; i++) {

                PGobject l = (PGobject) lines[i];
                RecordVistoryPO po = RecordVistoryRowTokenizer.DEFAULT_RECORD_VISTORY_TOKENIZER.process(l);
                if (Objects.nonNull(po)) {
                    result.add(po);
                }
            }

            return result;
        } finally {
            origins.free();
        }
    }
}
