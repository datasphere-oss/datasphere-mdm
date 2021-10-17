

/**
 *
 */
package org.datasphere.mdm.data.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.data.po.EtalonRecordDraftStatePO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.datasphere.mdm.core.type.data.RecordStatus;


/**
 * @author Mikhail Mikhailov
 * Etalon draft state row mapper.
 */
public class EtalonRecordDraftStateRowMapper implements RowMapper<EtalonRecordDraftStatePO> {

        /**
     * Default row mapper.
     */
    public static final EtalonRecordDraftStateRowMapper DEFAULT_ROW_MAPPER = new EtalonRecordDraftStateRowMapper();

    /**
     * Extracts first result or returns null.
     */
    public static final ResultSetExtractor<EtalonRecordDraftStatePO> DEFAULT_FIRST_RESULT_EXTRACTOR
        = rs -> rs.next() ? DEFAULT_ROW_MAPPER.mapRow(rs, rs.getRow()) : null;

    /**
     * Constructor.
     */
    private EtalonRecordDraftStateRowMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EtalonRecordDraftStatePO mapRow(ResultSet rs, int rowNum) throws SQLException {

        EtalonRecordDraftStatePO po = new EtalonRecordDraftStatePO();
        po.setCreateDate(rs.getTimestamp(EtalonRecordDraftStatePO.FIELD_CREATE_DATE));
        po.setCreatedBy(rs.getString(EtalonRecordDraftStatePO.FIELD_CREATED_BY));
        po.setEtalonId(rs.getString(EtalonRecordDraftStatePO.FIELD_ETALON_ID));
        po.setId(rs.getInt(EtalonRecordDraftStatePO.FIELD_ID));
        po.setRevision(rs.getInt(EtalonRecordDraftStatePO.FIELD_REVISION));
        po.setStatus(RecordStatus.valueOf(rs.getString(EtalonRecordDraftStatePO.FIELD_STATUS)));

        return po;
    }

}
