

package org.datasphere.mdm.data.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.storage.DataNodePO;
import org.springframework.jdbc.core.RowMapper;
import org.datasphere.mdm.system.dao.PoolSetting;
import org.datasphere.mdm.system.util.JsonUtils;

/**
 * @author Mikhail Mikhailov
 * Data node row mapper.
 */
public class DataNodeRowMapper implements RowMapper<DataNodePO> {
    /**
     * Default row mapper singletone.
     */
    public static final DataNodeRowMapper DEFAULT_ROW_MAPPER = new DataNodeRowMapper();
    /**
     * Pool settings type ref.
     */
    private static final TypeReference<Map<PoolSetting, String>> SETTINGS_TYPE_REFERENCE
        = new TypeReference<Map<PoolSetting, String>>(){};
    /**
     * Constructor.
     */
    public DataNodeRowMapper() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataNodePO mapRow(ResultSet rs, int rowNum) throws SQLException {

        DataNodePO po = new DataNodePO();
        po.setId(rs.getInt(DataNodePO.FIELD_ID));
        po.setName(rs.getString(DataNodePO.FIELD_NAME));
        po.setHost(rs.getString(DataNodePO.FIELD_HOST));
        po.setPort(rs.getInt(DataNodePO.FIELD_PORT));
        po.setDatabase(rs.getString(DataNodePO.FIELD_DATABASE));
        po.setUser(rs.getString(DataNodePO.FIELD_USER));
        po.setPassword(rs.getString(DataNodePO.FIELD_PASSWORD));
        po.setCreateDate(rs.getTimestamp(DataNodePO.FIELD_CREATE_DATE));
        po.setUpdateDate(rs.getTimestamp(DataNodePO.FIELD_UPDATE_DATE));

        String val = rs.getString(DataNodePO.FIELD_SETTINGS);
        if (!rs.wasNull() && StringUtils.isNotBlank(val)) {
            Map<PoolSetting, String> settings = JsonUtils.read(val, SETTINGS_TYPE_REFERENCE);
            po.setSettings(settings);
        }

        return po;
    }
}
