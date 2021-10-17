

/**
 *
 */
package org.datasphere.mdm.data.dao.rm;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.datasphere.mdm.data.dao.rm.tokenizer.RelationKeysRowTokenizer;
import org.datasphere.mdm.data.dao.rm.tokenizer.RelationOriginKeyRowTokenizer;
import org.datasphere.mdm.data.po.keys.RelationKeysPO;
import org.datasphere.mdm.data.po.keys.RelationOriginKeyPO;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author Mikhail Mikhailov
 * Mapper class for relation keys.
 */
public class RelationKeysRowMapper implements RowMapper<RelationKeysPO> {
    /**
     * Default row mapper.
     */
    public static final RelationKeysRowMapper DEFAULT_ROW_MAPPER = new RelationKeysRowMapper();
    /**
     * Default first result extractor.
     */
    public static final ResultSetExtractor<RelationKeysPO> DEFAULT_FIRST_RESULT_EXTRACTOR
        = rs -> rs.next() ? DEFAULT_ROW_MAPPER.mapRow(rs, rs.getRow()) : null;
    /**
     * Constructor.
     */
    private RelationKeysRowMapper() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationKeysPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return RelationKeysRowTokenizer.DEFAULT_RELATION_KEYS_TOKENIZER.process((PGobject) rs.getObject(1));
    }

    private List<RelationOriginKeyPO> getOriginKeys(Array origins) throws SQLException {

        if (Objects.isNull(origins)) {
            return Collections.emptyList();
        }

        try {

            Object[] lines = (Object[]) origins.getArray();
            List<RelationOriginKeyPO> result = new ArrayList<>(lines.length);
            for (int i = 0; i < lines.length; i++) {

                PGobject l = (PGobject) lines[i];
                RelationOriginKeyPO po = RelationOriginKeyRowTokenizer.DEFAULT_RELATION_ORIGIN_KEY_TOKENIZER.process(l);
                if (Objects.nonNull(po)) {
                    result.add(po);
                }
            }

            return result;
        } finally {
            origins.free();
        }
    }
}
