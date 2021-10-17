

package org.datasphere.mdm.data.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.data.po.data.RecordVistoryPO;
import org.datasphere.mdm.data.po.keys.AbstractVistoryPO;
import org.springframework.jdbc.core.RowMapper;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Extended stuff mapper.
 */
public class ExtendedRecordVistoryRowMapper
    extends AbstractVistoryRowMapper<RecordVistoryPO>
    implements RowMapper<RecordVistoryPO> {
    /**
     * Default 'binary protostuff data' row mapper.
     */
    public static final ExtendedRecordVistoryRowMapper DEFAULT_EXTENDED_RECORD_VISTORY_PROTOSTUFF_ROW_MAPPER
        = new ExtendedRecordVistoryRowMapper(true);
    /**
     * Default 'without data' row mapper.
     */
    public static final ExtendedRecordVistoryRowMapper DEFAULT_EXTENDED_RECORD_VISTORY_NO_DATA_ROW_MAPPER
        = new ExtendedRecordVistoryRowMapper(false);
    /**
     * Constructor.
     */
    protected ExtendedRecordVistoryRowMapper(boolean protostuffData) {
        super(protostuffData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordVistoryPO mapRow(ResultSet rs, int rowNum) throws SQLException {

        RecordVistoryPO po = new RecordVistoryPO();
        super.mapRow(po, rs, rowNum);

        po.setUpdateDate(rs.getTimestamp(AbstractVistoryPO.FIELD_UPDATE_DATE));
        po.setUpdatedBy(rs.getString(AbstractVistoryPO.FIELD_UPDATED_BY));
        po.setExternalId(rs.getString(RecordVistoryPO.FIELD_EXTERNAL_ID));
        po.setName(rs.getString(AbstractVistoryPO.FIELD_NAME));
        po.setSourceSystem(rs.getString(AbstractVistoryPO.FIELD_SOURCE_SYSTEM));
        po.setEnrichment(rs.getBoolean(AbstractVistoryPO.FIELD_IS_ENRICHMENT));
        po.setOriginStatus(RecordStatus.valueOf(rs.getString(AbstractVistoryPO.FIELD_ORIGIN_STATUS)));

        return po;
    }

}
