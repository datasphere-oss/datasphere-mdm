

/**
 *
 */
package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.security.UserEventPO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


/**
 * @author Mikhail Mikhailov
 *
 */
public class UserEventRowMapper implements RowMapper<UserEventPO> {

    /**
     * Default row mapper.
     */
    public static final RowMapper<UserEventPO> DEFAULT_ROW_MAPPER = new UserEventRowMapper();
    /**
     * Default extractor.
     */
    public static final ResultSetExtractor<UserEventPO> DEFAULT_RESULT_SET_EXTRACTOR
        = new ResultSetExtractor<UserEventPO>() {

        @Override
        public UserEventPO extractData(ResultSet rs) throws SQLException {
            return rs != null && rs.next() ? DEFAULT_ROW_MAPPER.mapRow(rs, rs.getRow()) : null;
        }
    };
    /**
     * Constructor.
     */
    private UserEventRowMapper() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
     */
    @Override
    public UserEventPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserEventPO po = new UserEventPO();

        po.setId(rs.getString(UserEventPO.FIELD_ID));
        po.setUserId(rs.getInt(UserEventPO.FIELD_USER_ID));
        po.setBinaryDataId(rs.getString(UserEventPO.FIELD_BINARY_DATA_ID));
        po.setCharacterDataId(rs.getString(UserEventPO.FIELD_CHARACTER_DATA_ID));
        po.setContent(rs.getString(UserEventPO.FIELD_CONTENT));
        po.setDetails(rs.getString(UserEventPO.FIELD_DETAILS));
        po.setType(rs.getString(UserEventPO.FIELD_TYPE));
        po.setCreateDate(rs.getTimestamp(UserEventPO.FIELD_CREATE_DATE));
        po.setCreatedBy(rs.getString(UserEventPO.FIELD_CREATED_BY));
        po.setUpdateDate(rs.getTimestamp(UserEventPO.FIELD_UPDATE_DATE));
        po.setUpdatedBy(rs.getString(UserEventPO.FIELD_UPDATED_BY));

        return po;
    }

}
