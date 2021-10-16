

package org.datasphere.mdm.core.dao.rm;

import org.datasphere.mdm.core.po.CustomStorageRecordPO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Dmitry Kopin
 * Mapper for custom settings object
 */
public class CustomSettingsObjectRowMapper implements RowMapper<CustomStorageRecordPO> {



    /**
     * Default reusable row mapper.
     */
    public static final CustomSettingsObjectRowMapper DEFAULT_ROW_MAPPER = new CustomSettingsObjectRowMapper();

    /**
     * Constructor.
     */
    public CustomSettingsObjectRowMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomStorageRecordPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomStorageRecordPO result = new CustomStorageRecordPO();
        result.setKey(rs.getString(CustomStorageRecordPO.FIELD_KEY));
        if(Objects.equals(result.getKey(), "")){
            result.setKey(null);
        }
        result.setUser(rs.getString(CustomStorageRecordPO.FIELD_USER_NAME));
        if(Objects.equals(result.getUser(), "")){
            result.setUser(null);
        }
        result.setValue(rs.getString(CustomStorageRecordPO.FIELD_VALUE));
        result.setUpdateDate(rs.getTimestamp(CustomStorageRecordPO.FIELD_UPDATE_DATE));
        return result;
    }

}
