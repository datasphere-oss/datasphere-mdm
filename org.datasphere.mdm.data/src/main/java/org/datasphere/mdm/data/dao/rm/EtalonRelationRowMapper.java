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

import org.datasphere.mdm.data.po.data.RelationEtalonPO;
import org.datasphere.mdm.data.type.data.RelationType;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.datasphere.mdm.core.dao.rm.AbstractDistributedUpdateableRowMapper;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Etalon relations row mapper.
 */
public class EtalonRelationRowMapper
    extends AbstractDistributedUpdateableRowMapper<RelationEtalonPO>
    implements RowMapper<RelationEtalonPO> {

    /**
     * Default 'with data' row mapper.
     */
    public static final EtalonRelationRowMapper DEFAULT_ETALON_RELATION_ROW_MAPPER
        = new EtalonRelationRowMapper();

    /**
     * Extracts first result or returns null.
     */
    public static final ResultSetExtractor<RelationEtalonPO> DEFAULT_ETALON_RELATION_FIRST_RESULT_EXTRACTOR
        = rs -> rs != null && rs.next() ? DEFAULT_ETALON_RELATION_ROW_MAPPER.mapRow(rs, rs.getRow()) : null;

    /**
     * Constructor.
     */
    public EtalonRelationRowMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationEtalonPO mapRow(ResultSet rs, int rowNum) throws SQLException {

        RelationEtalonPO po = new RelationEtalonPO();

        super.mapRow(po, rs, rowNum);

        po.setLsn(rs.getLong(RelationEtalonPO.FIELD_LSN));

        UUID val = rs.getObject(RelationEtalonPO.FIELD_ID, UUID.class);
        po.setId(val.toString());

        po.setName(rs.getString(RelationEtalonPO.FIELD_NAME));

        val = rs.getObject(RelationEtalonPO.FIELD_ETALON_ID_FROM, UUID.class);
        po.setFromEtalonId(val.toString());

        val = rs.getObject(RelationEtalonPO.FIELD_ETALON_ID_TO, UUID.class);
        po.setToEtalonId(val.toString());

        po.setStatus(RecordStatus.valueOf(rs.getString(RelationEtalonPO.FIELD_STATUS)));
        po.setRelationType(RelationType.fromValue(rs.getString(RelationEtalonPO.FIELD_RELTYPE)));
        po.setOperationId(rs.getString(RelationEtalonPO.FIELD_OPERATION_ID));

        return po;
    }

}
