

/**
 *
 */
package org.datasphere.mdm.data.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.data.po.EtalonRecordDraftStatePO;
import org.datasphere.mdm.data.po.EtalonRelationDraftStatePO;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.datasphere.mdm.core.type.data.RecordStatus;


/**
 * @author Mikhail Mikhailov
 * Etalon relation draft state row mapper.
 */
public class EtalonRelationDraftStateRowMapper implements RowMapper<EtalonRelationDraftStatePO> {

        /**
     * Default row mapper.
     */
    public static final EtalonRelationDraftStateRowMapper DEFAULT_ROW_MAPPER = new EtalonRelationDraftStateRowMapper();

    /**
     * Extracts first result or returns null.
     */
    public static final ResultSetExtractor<EtalonRelationDraftStatePO> DEFAULT_FIRST_RESULT_EXTRACTOR
        = rs -> rs.next() ? DEFAULT_ROW_MAPPER.mapRow(rs, rs.getRow()) : null;

    /**
     * Constructor.
     */
    private EtalonRelationDraftStateRowMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EtalonRelationDraftStatePO mapRow(ResultSet rs, int rowNum) throws SQLException {

        EtalonRelationDraftStatePO po = new EtalonRelationDraftStatePO();
        po.setCreateDate(rs.getTimestamp(EtalonRecordDraftStatePO.FIELD_CREATE_DATE));
        po.setCreatedBy(rs.getString(EtalonRecordDraftStatePO.FIELD_CREATED_BY));
        po.setEtalonId(rs.getString(EtalonRecordDraftStatePO.FIELD_ETALON_ID));
        po.setId(rs.getInt(EtalonRecordDraftStatePO.FIELD_ID));
        po.setRevision(rs.getInt(EtalonRecordDraftStatePO.FIELD_REVISION));
        po.setStatus(RecordStatus.valueOf(rs.getString(EtalonRecordDraftStatePO.FIELD_STATUS)));

        return po;
    }

}
