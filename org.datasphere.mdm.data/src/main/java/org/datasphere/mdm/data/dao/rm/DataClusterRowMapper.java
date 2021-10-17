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

import org.datasphere.mdm.data.po.storage.DataClusterPO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mikhail Mikhailov
 * Data storage cluster metadata row mapper.
 */
public class DataClusterRowMapper implements RowMapper<DataClusterPO> {
    /**
     * The default singleton.
     */
    public static final DataClusterRowMapper DEFAULT_ROW_MAPPER = new DataClusterRowMapper();
    /**
     * Default singleton extractor.
     */
    public static final ResultSetExtractor<DataClusterPO> DEFAULT_SINGLETON_EXTRACTOR
        = rs -> rs.next() ? DEFAULT_ROW_MAPPER.mapRow(rs, 1) : null;
    /**
     * Constructor.
     */
    public DataClusterRowMapper() {
        super();
    }

    @Override
    public DataClusterPO mapRow(ResultSet rs, int rowNum) throws SQLException {

        DataClusterPO po = new DataClusterPO();
        po.setId(rs.getInt(DataClusterPO.FIELD_ID));
        po.setName(rs.getString(DataClusterPO.FIELD_NAME));
        po.setNumberOfShards(rs.getInt(DataClusterPO.FIELD_NUMBER_OF_SHARDS));
        po.setDistributionFactor(rs.getInt(DataClusterPO.FIELD_DISTRIBUTION_FACTOR));
        po.setHasData(rs.getBoolean(DataClusterPO.FIELD_HAS_DATA));
        po.setInitialized(rs.getBoolean(DataClusterPO.FIELD_INITIALIZED));
        po.setVersion(rs.getInt(DataClusterPO.FIELD_VERSION));

        return po;
    }

}
