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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.data.po.data.RelationVistoryPO;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mikhail Mikhailov
 * Relations vistory row mapper.
 */
public class RelationVistoryRowMapper extends AbstractVistoryRowMapper<RelationVistoryPO> implements RowMapper<RelationVistoryPO> {

    /**
     * Default 'with Protostuff data' row mapper.
     */
    public static final RelationVistoryRowMapper DEFAULT_PROTOSTUFF_ROW_MAPPER
        = new RelationVistoryRowMapper( true);
    /**
     * Default 'without data' row mapper.
     */
    public static final RelationVistoryRowMapper DEFAULT_NO_DATA_ROW_MAPPER
        = new RelationVistoryRowMapper( false);
    /**
     * Default 'binary protostuff data' row mapper.
     */
    public static final RelationVistoryRowMapper RAW_PROTOSTUFF_ROW_MAPPER
        = new RelationVistoryRowMapper( true, true);
    /**
     * Constructor.
     */
    private RelationVistoryRowMapper( boolean protostuffData) {
        super(protostuffData);
    }
    /**
     * Constructor.
     */
    private RelationVistoryRowMapper(boolean protostuffData, boolean rawData) {
        super(protostuffData, rawData);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public RelationVistoryPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RelationVistoryPO po = new RelationVistoryPO();
        super.mapRow(po, rs, rowNum);
        return po;
    }

}
