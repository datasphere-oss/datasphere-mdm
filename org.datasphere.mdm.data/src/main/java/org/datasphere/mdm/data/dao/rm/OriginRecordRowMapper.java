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

import org.datasphere.mdm.data.po.data.RecordEtalonPO;
import org.datasphere.mdm.data.po.data.RecordOriginPO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.datasphere.mdm.core.dao.rm.AbstractDistributedUpdateableRowMapper;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Row mapper for {@link RecordEtalonPO} objects.
 */
public class OriginRecordRowMapper
    extends AbstractDistributedUpdateableRowMapper<RecordOriginPO>
    implements RowMapper<RecordOriginPO> {

    /**
     * Default reusable row mapper.
     */
    public static final OriginRecordRowMapper DEFAULT_ROW_MAPPER = new OriginRecordRowMapper();
    /**
     * Extracts first result or returns null.
     */
    public static final ResultSetExtractor<RecordOriginPO> DEFAULT_ORIGIN_FIRST_RESULT_EXTRACTOR
        = rs -> rs.next() ? DEFAULT_ROW_MAPPER.mapRow(rs, rs.getRow()) : null;

    /**
     * Constructor.
     */
    private OriginRecordRowMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordOriginPO mapRow(ResultSet rs, int rowNum) throws SQLException {

        RecordOriginPO po = new RecordOriginPO();

        super.mapRow(po, rs, rowNum);

        po.setId(rs.getString(RecordOriginPO.FIELD_ID));
        po.setInitialOwner(rs.getObject(RecordOriginPO.FIELD_INITIAL_OWNER, UUID.class));
        po.setEtalonId(rs.getString(RecordOriginPO.FIELD_ETALON_ID));

        String externalId = rs.getString(RecordOriginPO.FIELD_EXTERNAL_ID);
        String entityName = rs.getString(RecordOriginPO.FIELD_NAME);
        String sourceSystem = rs.getString(RecordOriginPO.FIELD_SOURCE_SYSTEM);

        po.setExternalId(externalId, entityName, sourceSystem);
        po.setEnrichment(rs.getBoolean(RecordOriginPO.FIELD_ENRICHMENT));
        po.setStatus(RecordStatus.valueOf(rs.getString(RecordOriginPO.FIELD_STATUS)));

        return po;
    }

}
