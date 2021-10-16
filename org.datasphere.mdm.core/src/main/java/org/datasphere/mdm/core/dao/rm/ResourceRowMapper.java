

package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.security.ResourcePO;
import org.springframework.jdbc.core.RowMapper;

/**
 * Row mapper for the ResourcePO object.
 * @author ilya.bykov
 */
public class ResourceRowMapper implements RowMapper<ResourcePO>{

    /**
     * Default row mapper.
     */
    public static final ResourceRowMapper DEFAULT_ROW_MAPPER
        = new ResourceRowMapper();

    /**
     * Constructor.
     */
    private ResourceRowMapper() {
        super();
    }

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ResourcePO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResourcePO result = new ResourcePO();
		result.setId(rs.getInt(ResourcePO.Fields.ID));
		result.setParentId(rs.getInt(ResourcePO.Fields.PARENT_ID) == 0 ? null : rs.getInt(ResourcePO.Fields.PARENT_ID));
		result.setName(rs.getString(ResourcePO.Fields.NAME));
		result.setRType(rs.getString(ResourcePO.Fields.R_TYPE));
		result.setCategory(rs.getString(ResourcePO.Fields.CATEGORY));
		result.setDisplayName(rs.getString(ResourcePO.Fields.DISPLAY_NAME));
		result.setCreatedAt(rs.getTimestamp(ResourcePO.Fields.CREATED_AT));
		result.setUpdatedAt(rs.getTimestamp(ResourcePO.Fields.UPDATED_AT));
		result.setCreatedBy(rs.getString(ResourcePO.Fields.CREATED_BY));
		result.setUpdatedBy(rs.getString(ResourcePO.Fields.UPDATED_BY));
		return result;
	}

}
