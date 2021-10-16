

package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.security.LabelPO;
import org.springframework.jdbc.core.RowMapper;

/**
 * Row mapper for the LabelPO object.
 * @author ilya.bykov
 */
public class LabelRowMapper implements RowMapper<LabelPO> {

    /**
     * RM singleton.
     */
    public static final LabelRowMapper DEFAULT_LABEL_ROW_MAPPER = new LabelRowMapper();

    /**
     * Default disabled constructor.
     */
    private LabelRowMapper() {
        super();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
     * int)
     */
    @Override
    public LabelPO mapRow(ResultSet rs, int rowNum) throws SQLException {

        LabelPO result = new LabelPO();
        result.setId(rs.getInt(LabelPO.Fields.ID));
        result.setName(rs.getString(LabelPO.Fields.NAME));
        result.setDisplayName(rs.getString(LabelPO.Fields.DISPLAY_NAME));
        result.setDescription(rs.getString(LabelPO.Fields.DESCRIPTION));
        result.setCreatedAt(rs.getTimestamp(LabelPO.Fields.CREATED_AT));
        result.setUpdatedAt(rs.getTimestamp(LabelPO.Fields.UPDATED_AT));
        result.setCreatedBy(rs.getString(LabelPO.Fields.CREATED_BY));
        result.setUpdatedBy(rs.getString(LabelPO.Fields.UPDATED_BY));

        return result;
    }

}
