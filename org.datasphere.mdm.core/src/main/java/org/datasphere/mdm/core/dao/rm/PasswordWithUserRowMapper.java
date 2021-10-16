

package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.security.PasswordPO;
import org.datasphere.mdm.core.po.security.UserPO;

public class PasswordWithUserRowMapper extends PasswordRowMapper {
    @Override
    public PasswordPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        final PasswordPO passwordPO = super.mapRow(rs, rowNum);
        final UserPO user = new UserPO();
        user.setId(rs.getInt("userId"));
        user.setLogin(rs.getString(UserPO.Fields.LOGIN));
        user.setEmail(rs.getString(UserPO.Fields.EMAIL));
        user.setEmailNotification(rs.getBoolean(UserPO.Fields.EMAIL_NOTIFICATION));
        user.setLocale(rs.getString(UserPO.Fields.LOCALE));
        user.setAdmin(rs.getBoolean(UserPO.Fields.ADMIN));

        passwordPO.setUser(user);
        return passwordPO;
    }
}
