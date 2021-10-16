

/**
 * Date: 05.07.2016
 */

package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.security.UserPropertyPO;
import org.datasphere.mdm.core.po.security.UserPropertyPO.FieldColumns;
import org.springframework.jdbc.core.RowMapper;

/**
 * FIXDOC: add file description.
 *
 * @author amagdenko
 */
public class UserPropertyRowMapper implements RowMapper<UserPropertyPO> {
    @Override
    public UserPropertyPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserPropertyPO result = new UserPropertyPO();

        result.setId(rs.getLong(FieldColumns.ID.name()));
        result.setName(rs.getString(FieldColumns.NAME.name()));
        result.setDisplayName(rs.getString(FieldColumns.DISPLAY_NAME.name()));
        result.setRequired(rs.getBoolean(FieldColumns.REQUIRED.name()));
        result.setReadOnly(rs.getBoolean(FieldColumns.READ_ONLY.name()));
        result.setFieldType(rs.getString(FieldColumns.FIELD_TYPE.name()));

        return result;
    }
}
