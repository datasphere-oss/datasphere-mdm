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

import org.datasphere.mdm.data.po.data.RecordVistoryPO;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mikhail Mikhailov
 * Origins vistory row mapper.
 */
public class RecordVistoryRowMapper extends AbstractVistoryRowMapper<RecordVistoryPO> implements RowMapper<RecordVistoryPO> {

    /**
     * Default 'binary protostuff data' row mapper.
     */
    public static final RecordVistoryRowMapper DEFAULT_PROTOSTUFF_ROW_MAPPER
        = new RecordVistoryRowMapper( true);
    /**
     * Default 'binary protostuff data' row mapper.
     */
    public static final RecordVistoryRowMapper RAW_PROTOSTUFF_ROW_MAPPER
        = new RecordVistoryRowMapper( true, true);
    /**
     * Default 'without data' row mapper.
     */
    public static final RecordVistoryRowMapper DEFAULT_NO_DATA_ROW_MAPPER
        = new RecordVistoryRowMapper( false);
    /**
     * Constructor.
     */
    protected RecordVistoryRowMapper(boolean protostuffData) {
        super(protostuffData);
    }
    /**
     * Constructor.
     */
    protected RecordVistoryRowMapper(boolean protostuffData, boolean rawData) {
        super(protostuffData, rawData);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public RecordVistoryPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecordVistoryPO po = new RecordVistoryPO();
        super.mapRow(po, rs, rowNum);
        return po;
    }
}
