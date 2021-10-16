

package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.security.RolePO;
import org.springframework.jdbc.core.RowMapper;

/**
 *  Row mapper for the RolePO object.
 *  @author ilya.bykov
 */
public class RoleRowMapper implements RowMapper<RolePO> {

    /**
     * Default row mapper.
     */
    public static final RoleRowMapper DEFAULT_ROLE_ROW_MAPPER = new RoleRowMapper();

    /**
     * Constructor.
     */
    private RoleRowMapper() {
        super();
    }
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public RolePO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RolePO result = new RolePO();
		result.setId(rs.getInt(RolePO.Fields.ID));
		result.setName(rs.getString(RolePO.Fields.NAME));
		result.setRType(rs.getString(RolePO.Fields.R_TYPE));
		result.setDisplayName(rs.getString(RolePO.Fields.DISPLAY_NAME));
		result.setDescription(rs.getString(RolePO.Fields.DESCRIPTION));
		result.setCreatedAt(rs.getTimestamp(RolePO.Fields.CREATED_AT));
		result.setUpdatedAt(rs.getTimestamp(RolePO.Fields.UPDATED_AT));
		result.setCreatedBy(rs.getString(RolePO.Fields.CREATED_BY));
		result.setUpdatedBy(rs.getString(RolePO.Fields.UPDATED_BY));
		return result;
	}

}
