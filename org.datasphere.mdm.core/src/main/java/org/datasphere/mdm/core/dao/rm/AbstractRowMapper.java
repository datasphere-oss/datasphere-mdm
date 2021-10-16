

package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.AbstractObjectPO;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mikhail Mikhailov
 * Abstract PO mapper.
 */
public abstract class AbstractRowMapper<T extends AbstractObjectPO> implements RowMapper<T> {

    /**
     * Constructor.
     */
    public AbstractRowMapper() {
        super();
    }

    /**
     * Maps common rows.
     * @param t object
     * @param rs result set
     * @param rowNum row number
     * @throws SQLException if something went wrong
     */
    protected void mapRow(T t, ResultSet rs, int rowNum) throws SQLException {
        t.setCreateDate(rs.getTimestamp(AbstractObjectPO.FIELD_CREATE_DATE));
        t.setCreatedBy(rs.getString(AbstractObjectPO.FIELD_CREATED_BY));
        t.setUpdateDate(rs.getTimestamp(AbstractObjectPO.FIELD_UPDATE_DATE));
        t.setUpdatedBy(rs.getString(AbstractObjectPO.FIELD_UPDATED_BY));
    }
}
