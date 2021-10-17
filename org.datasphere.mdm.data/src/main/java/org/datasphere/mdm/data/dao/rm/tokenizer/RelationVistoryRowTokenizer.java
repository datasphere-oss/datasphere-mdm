

package org.datasphere.mdm.data.dao.rm.tokenizer;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.data.RelationVistoryPO;
import org.datasphere.mdm.data.serialization.DataSerializer;
import org.postgresql.util.PGbytea;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueTokenizer;
import org.datasphere.mdm.core.dao.tokenizer.RowTokenizerField;

/**
 * @author Mikhail Mikhailov
 * Relation vistory tokenizer.
 */
public class RelationVistoryRowTokenizer extends AbstractVistoryRowTokenizer<RelationVistoryPO> {
    /**
     * The default static tokenizer.
     */
    public static final RelationVistoryRowTokenizer DEFAULT_RELATION_VISTORY_TOKENIZER
        = new RelationVistoryRowTokenizer();

    /**
     * Protostuff data.
     */
    private static final RowTokenizerField<RelationVistoryPO> DATA_B = (v, po) -> {

        if (StringUtils.isBlank(v)) {
            return;
        }

        try {
            po.setData(DataSerializer.fromProtostuff(PGbytea.toBytes(CompositeValueTokenizer.stripBytea(v).trim().getBytes())));
        } catch(SQLException sqle) {
            // Just suppress
        }
    };

    private static final RowTokenizerField<RelationVistoryPO> MAJOR = (v, po) -> po.setMajor(Integer.parseInt(v));
    private static final RowTokenizerField<RelationVistoryPO> MINOR = (v, po) -> po.setMinor(Integer.parseInt(v));

    private static final RowTokenizerField<RelationVistoryPO>[] FIELDS = RowTokenizerField.fields(DATA_B, MAJOR, MINOR);
    /**
     * Constructor.
     */
    private RelationVistoryRowTokenizer() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return super.size() + FIELDS.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected RelationVistoryPO process(CompositeValueTokenizer fields) {

        int sz = size();
        if (fields.getSize() != sz) {
            return null;
        }

        CompositeValueIterator rti = new CompositeValueIterator(fields);
        RelationVistoryPO po = new RelationVistoryPO();
        super.process(rti, po);
        super.process(rti, po, FIELDS);

        return po;
    }
}
