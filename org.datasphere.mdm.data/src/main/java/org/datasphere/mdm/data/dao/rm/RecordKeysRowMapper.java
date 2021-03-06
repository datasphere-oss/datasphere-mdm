

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

import org.datasphere.mdm.data.dao.rm.tokenizer.RecordKeysRowTokenizer;
import org.datasphere.mdm.data.po.keys.RecordKeysPO;
import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.datasphere.mdm.core.dao.rm.AbstractRowMapper;

/**
 * @author Mikhail Mikhailov
 * Record keys.
 */
public class RecordKeysRowMapper extends AbstractRowMapper<RecordKeysPO> implements RowMapper<RecordKeysPO> {
    /**
     * Default reusable row mapper.
     */
    public static final RecordKeysRowMapper DEFAULT_ROW_MAPPER = new RecordKeysRowMapper();
    /**
     * Extracts first result or returns null.
     */
    public static final ResultSetExtractor<RecordKeysPO> DEFAULT_FIRST_RESULT_EXTRACTOR = rs -> rs.next()
            ? DEFAULT_ROW_MAPPER.mapRow(rs, rs.getRow())
            : null;
    /**
     * Extracts first result or returns null.
     */
    public static final ResultSetExtractor<List<RecordKeysPO>> DEFAULT_ARRAY_RESULT_EXTRACTOR = rs -> {

        if (!rs.next()) {
            return Collections.emptyList();
        }

        Array keys = rs.getArray(1);
        return DEFAULT_ROW_MAPPER.getKeys(keys);
    };
    /**
     * Constructor.
     */
    private RecordKeysRowMapper() {
        super();
    }
    /**
     * Unmarshall keys array.
     * @param keys the keys array
     * @return list of keys
     * @throws SQLException
     */
    private List<RecordKeysPO> getKeys(Array keys) throws SQLException {

        if (Objects.isNull(keys)) {
            return Collections.emptyList();
        }

        try {

            Object[] lines = (Object[]) keys.getArray();
            List<RecordKeysPO> result = new ArrayList<>(lines.length);
            for (int i = 0; i < lines.length; i++) {

                PGobject l = (PGobject) lines[i];
                RecordKeysPO po = RecordKeysRowTokenizer.DEFAULT_RECORD_KEYS_TOKENIZER.process(l);
                if (Objects.nonNull(po)) {
                    result.add(po);
                }
            }

            return result;
        } finally {
            keys.free();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public RecordKeysPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        // The keys top level
        return RecordKeysRowTokenizer.DEFAULT_RECORD_KEYS_TOKENIZER.process((PGobject) rs.getObject(1));
    }
}
