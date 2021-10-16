

package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.security.LabelAttributePO;
import org.datasphere.mdm.core.po.security.LabelAttributeValuePO;
import org.springframework.jdbc.core.RowMapper;

/**
 * Row mapper for the LabelPO object.
 * @author ilya.bykov
 */
public class LabelAttributeValueRowMapper implements RowMapper<LabelAttributeValuePO> {

    /**
     * Instance.
     */
    public static final LabelAttributeValueRowMapper DEFAULT_ROW_MAPPER
        = new LabelAttributeValueRowMapper();

    /**
     * Constructor.
     */
    private LabelAttributeValueRowMapper() {
        super();
    }
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public LabelAttributeValuePO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LabelAttributeValuePO result = new LabelAttributeValuePO();
		result.setId(rs.getInt(LabelAttributeValuePO.Fields.ID));
		result.setValue(rs.getString(LabelAttributeValuePO.Fields.VALUE));
		result.setCreatedAt(rs.getTimestamp(LabelAttributeValuePO.Fields.CREATED_AT));
		result.setUpdatedAt(rs.getTimestamp(LabelAttributeValuePO.Fields.UPDATED_AT));
		result.setCreatedBy(rs.getString(LabelAttributeValuePO.Fields.CREATED_BY));
		result.setUpdatedBy(rs.getString(LabelAttributeValuePO.Fields.UPDATED_BY));
		result.setGroup(rs.getInt(LabelAttributeValuePO.Fields.S_LABEL_GROUP));
		result.setLabelAttribute(new LabelAttributePO(rs.getInt(LabelAttributeValuePO.Fields.S_LABEL_ATTRIBUTE_ID)));
		return result;
	}

}
