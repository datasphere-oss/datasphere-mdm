

package org.datasphere.mdm.data.dao.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.datasphere.mdm.data.po.data.RelationVistoryPO;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mikhail Mikhailov
 * Relations vistory row mapper.
 */
public class RelationVistoryRowMapper extends AbstractVistoryRowMapper<RelationVistoryPO> implements RowMapper<RelationVistoryPO> {

    /**
     * Default 'with Protostuff data' row mapper.
     */
    public static final RelationVistoryRowMapper DEFAULT_PROTOSTUFF_ROW_MAPPER
        = new RelationVistoryRowMapper( true);
    /**
     * Default 'without data' row mapper.
     */
    public static final RelationVistoryRowMapper DEFAULT_NO_DATA_ROW_MAPPER
        = new RelationVistoryRowMapper( false);
    /**
     * Default 'binary protostuff data' row mapper.
     */
    public static final RelationVistoryRowMapper RAW_PROTOSTUFF_ROW_MAPPER
        = new RelationVistoryRowMapper( true, true);
    /**
     * Constructor.
     */
    private RelationVistoryRowMapper( boolean protostuffData) {
        super(protostuffData);
    }
    /**
     * Constructor.
     */
    private RelationVistoryRowMapper(boolean protostuffData, boolean rawData) {
        super(protostuffData, rawData);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public RelationVistoryPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        RelationVistoryPO po = new RelationVistoryPO();
        super.mapRow(po, rs, rowNum);
        return po;
    }

}
