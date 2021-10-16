

package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.security.PasswordPO;
import org.datasphere.mdm.core.po.security.UserPO;
import org.springframework.jdbc.core.RowMapper;

/**
 * Row mapper for the ResourcePO object.
 * @author ilya.bykov
 */
public class PasswordRowMapper implements RowMapper<PasswordPO>{

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public PasswordPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        PasswordPO result = new PasswordPO();
        result.setId(rs.getInt(PasswordPO.Fields.ID));
        result.setActive(rs.getBoolean(PasswordPO.Fields.ACTIVE));
        result.setPasswordText(rs.getString(PasswordPO.Fields.PASSWORD_TEXT));
        result.setCreatedAt(rs.getTimestamp(PasswordPO.Fields.CREATED_AT));
        result.setUpdatedAt(rs.getTimestamp(PasswordPO.Fields.UPDATED_AT));
        result.setCreatedBy(rs.getString(PasswordPO.Fields.CREATED_BY));
        result.setUpdatedBy(rs.getString(PasswordPO.Fields.UPDATED_BY));
        return result;
    }

}
