

/**
 *
 */
package org.datasphere.mdm.data.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.data.po.data.RecordVistoryPO;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mikhail Mikhailov
 * Origins vistory row mapper.
 */
public class RecordVistoryRowMapper extends AbstractVistoryRowMapper<RecordVistoryPO> implements RowMapper<RecordVistoryPO> {

    /**
     * Default 'binary protostuff data' row mapper.
     */
    public static final RecordVistoryRowMapper DEFAULT_PROTOSTUFF_ROW_MAPPER
        = new RecordVistoryRowMapper( true);
    /**
     * Default 'binary protostuff data' row mapper.
     */
    public static final RecordVistoryRowMapper RAW_PROTOSTUFF_ROW_MAPPER
        = new RecordVistoryRowMapper( true, true);
    /**
     * Default 'without data' row mapper.
     */
    public static final RecordVistoryRowMapper DEFAULT_NO_DATA_ROW_MAPPER
        = new RecordVistoryRowMapper( false);
    /**
     * Constructor.
     */
    protected RecordVistoryRowMapper(boolean protostuffData) {
        super(protostuffData);
    }
    /**
     * Constructor.
     */
    protected RecordVistoryRowMapper(boolean protostuffData, boolean rawData) {
        super(protostuffData, rawData);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public RecordVistoryPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecordVistoryPO po = new RecordVistoryPO();
        super.mapRow(po, rs, rowNum);
        return po;
    }
}
