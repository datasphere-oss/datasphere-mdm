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

package org.datasphere.mdm.data.dao.rm;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.datasphere.mdm.data.dao.rm.tokenizer.RelationTimelineRowTokenizer;
import org.datasphere.mdm.data.dao.rm.tokenizer.RelationVistoryRowTokenizer;
import org.datasphere.mdm.data.po.data.RelationTimelinePO;
import org.datasphere.mdm.data.po.data.RelationVistoryPO;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mikhail Mikhailov
 *
 */
public class RelationTimelineRowMapper implements RowMapper<RelationTimelinePO> {
    /**
     * Default row mapper.
     */
    public static final RelationTimelineRowMapper DEFAULT_RELATION_TIMELINE_ROW_MAPPER = new RelationTimelineRowMapper();
    /**
     * Constructor.
     */
    private RelationTimelineRowMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationTimelinePO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RelationTimelineRowTokenizer.DEFAULT_RELATION_TIMELINE_TOKENIZER.process((PGobject) rs.getObject(1));
        /*
        RelationTimelinePO po = new RelationTimelinePO();

        // 1. Keys
        Object pgKeys = rs.getObject(RelationTimelinePO.FIELD_KEYS);
        if (!rs.wasNull()) {
            RelationKeysPO keys = RelationKeysRowTokenizer.DEFAULT_RELATION_KEYS_TOKENIZER.process((PGobject) pgKeys);
            po.setKeys(keys);
        }

        // 2. Vistory array
        Array vistory = rs.getArray(RelationTimelinePO.FIELD_VISTORY_DATA);
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
    private List<RelationVistoryPO> getVistory(Array origins) throws SQLException {

        if (Objects.isNull(origins)) {
            return Collections.emptyList();
        }

        try {

            Object[] lines = (Object[]) origins.getArray();
            List<RelationVistoryPO> result = new ArrayList<>(lines.length);
            for (int i = 0; i < lines.length; i++) {

                PGobject l = (PGobject) lines[i];
                RelationVistoryPO po = RelationVistoryRowTokenizer.DEFAULT_RELATION_VISTORY_TOKENIZER.process(l);
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
