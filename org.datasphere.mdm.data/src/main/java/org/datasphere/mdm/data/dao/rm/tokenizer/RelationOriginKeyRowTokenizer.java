

package org.datasphere.mdm.data.dao.rm.tokenizer;

import java.util.UUID;
import java.util.function.BiConsumer;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.keys.RelationOriginKeyPO;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueTokenizer;

/**
 * @author Mikhail Mikhailov
 * Record origin keyz row tokenizer.
 */
public class RelationOriginKeyRowTokenizer extends AbstractOriginKeyRowTokenizer<RelationOriginKeyPO> {
    /**
     * Default tokenizer.
     */
    public static final RelationOriginKeyRowTokenizer DEFAULT_RELATION_ORIGIN_KEY_TOKENIZER
        = new RelationOriginKeyRowTokenizer();
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the declaration.
     * from_key uuid,
     * to_key uuid
     */
    enum RelationOriginKeyFields {
        FROM_KEY((v, po) -> po.setFromKey(UUID.fromString(v))),
        TO_KEY((v, po) -> po.setToKey(UUID.fromString(v)));

        RelationOriginKeyFields(BiConsumer<String, RelationOriginKeyPO> f) {
            this.converter = f;
        }

        private BiConsumer<String, RelationOriginKeyPO> converter;
        public BiConsumer<String, RelationOriginKeyPO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    public RelationOriginKeyRowTokenizer() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return RelationOriginKeyFields.values().length + super.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected RelationOriginKeyPO process(CompositeValueTokenizer fields) {

        int sz = size();
        if (fields.getSize() != sz) {
            return null;
        }

        CompositeValueIterator tk = new CompositeValueIterator(fields);
        RelationOriginKeyPO po = new RelationOriginKeyPO();
        super.process(tk, po);

        for (int i = 0; i < RelationOriginKeyFields.values().length && tk.hasNext(); i++) {

            String token = tk.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            RelationOriginKeyFields.values()[i].consumer().accept(token, po);
        }

        return po;
    }

}
