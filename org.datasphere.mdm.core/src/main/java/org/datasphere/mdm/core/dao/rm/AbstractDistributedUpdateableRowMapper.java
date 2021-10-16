

package org.datasphere.mdm.core.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.core.po.AbstractDistributedUpdateablePO;

/**
 * @author Mikhail Mikhailov
 * Abstract PO mapper.
 */
public abstract class AbstractDistributedUpdateableRowMapper<T extends AbstractDistributedUpdateablePO> {

    /**
     * Constructor.
     */
    public AbstractDistributedUpdateableRowMapper() {
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
        t.setCreateDate(rs.getTimestamp(AbstractDistributedUpdateablePO.FIELD_CREATE_DATE));
        t.setCreatedBy(rs.getString(AbstractDistributedUpdateablePO.FIELD_CREATED_BY));
        t.setUpdateDate(rs.getTimestamp(AbstractDistributedUpdateablePO.FIELD_UPDATE_DATE));
        t.setUpdatedBy(rs.getString(AbstractDistributedUpdateablePO.FIELD_UPDATED_BY));
        t.setShard(rs.getInt(AbstractDistributedUpdateablePO.FIELD_SHARD));
    }
}
