
/**
 *
 */
package org.datasphere.mdm.data.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.data.po.data.RecordEtalonPO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.datasphere.mdm.core.dao.rm.AbstractDistributedUpdateableRowMapper;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Row mapper for {@link RecordEtalonPO} objects.
 */
public class EtalonRecordRowMapper
    extends AbstractDistributedUpdateableRowMapper<RecordEtalonPO>
    implements RowMapper<RecordEtalonPO> {

    /**
     * Default reusable row mapper.
     */
    public static final EtalonRecordRowMapper DEFAULT_ROW_MAPPER = new EtalonRecordRowMapper();

    /**
     * Extracts first result or returns null.
     */
    public static final ResultSetExtractor<RecordEtalonPO> DEFAULT_ETALON_FIRST_RESULT_EXTRACTOR
        = rs -> rs.next() ? DEFAULT_ROW_MAPPER.mapRow(rs, rs.getRow()) : null;

    /**
     * Constructor.
     */
    public EtalonRecordRowMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordEtalonPO mapRow(ResultSet rs, int rowNum) throws SQLException {

        RecordEtalonPO po = new RecordEtalonPO();

        super.mapRow(po, rs, rowNum);

        po.setId(rs.getString(RecordEtalonPO.FIELD_ID));
        po.setName(rs.getString(RecordEtalonPO.FIELD_NAME));
        po.setStatus(RecordStatus.valueOf(rs.getString(RecordEtalonPO.FIELD_STATUS)));
//        po.setApproval(ApprovalState.valueOf(rs.getString(RecordEtalonPO.FIELD_APPROVAL)));
        po.setLsn(rs.getLong(RecordEtalonPO.FIELD_LSN));
        po.setOperationId(rs.getString(RecordEtalonPO.FIELD_OPERATION_ID));

        return po;
    }

}
