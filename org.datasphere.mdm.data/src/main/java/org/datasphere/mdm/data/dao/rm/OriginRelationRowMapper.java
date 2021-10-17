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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.datasphere.mdm.data.po.data.RelationOriginPO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.datasphere.mdm.core.dao.rm.AbstractDistributedUpdateableRowMapper;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Origin relations row mapper.
 */
public class OriginRelationRowMapper
    extends AbstractDistributedUpdateableRowMapper<RelationOriginPO>
    implements RowMapper<RelationOriginPO> {

    /**
     * Default 'with data' row mapper.
     */
    public static final OriginRelationRowMapper DEFAULT_ORIGIN_RELATION_ROW_MAPPER
        = new OriginRelationRowMapper();
    /**
     * Extracts first result or returns null.
     */
    public static final ResultSetExtractor<RelationOriginPO> DEFAULT_ORIGIN_RELATION_FIRST_RESULT_EXTRACTOR
        = rs -> rs != null && rs.next() ? DEFAULT_ORIGIN_RELATION_ROW_MAPPER.mapRow(rs, rs.getRow()) : null;
    /**
     * Constructor.
     */
    private OriginRelationRowMapper() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public RelationOriginPO mapRow(ResultSet rs, int rowNum) throws SQLException {

        RelationOriginPO po = new RelationOriginPO();

        super.mapRow(po, rs, rowNum);

        po.setId(rs.getString(RelationOriginPO.FIELD_ID));
        po.setInitialOwner(rs.getObject(RelationOriginPO.FIELD_INITIAL_OWNER, UUID.class));
        po.setEtalonId(rs.getString(RelationOriginPO.FIELD_ETALON_ID));
        po.setName(rs.getString(RelationOriginPO.FIELD_NAME));
        po.setFromOriginId(rs.getString(RelationOriginPO.FIELD_ORIGIN_ID_FROM));
        po.setToOriginId(rs.getString(RelationOriginPO.FIELD_ORIGIN_ID_TO));
        po.setSourceSystem(rs.getString(RelationOriginPO.FIELD_SOURCE_SYSTEM));
        po.setStatus(RecordStatus.valueOf(rs.getString(RelationOriginPO.FIELD_STATUS)));
        po.setEnrichment(rs.getBoolean(RelationOriginPO.FIELD_ENRICHMENT));

        return po;
    }

}
