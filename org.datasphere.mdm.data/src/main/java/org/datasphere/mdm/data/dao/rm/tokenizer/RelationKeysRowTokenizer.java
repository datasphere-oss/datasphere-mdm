
package org.datasphere.mdm.data.dao.rm.tokenizer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.keys.RecordKeysPO;
import org.datasphere.mdm.data.po.keys.RelationKeysPO;
import org.datasphere.mdm.data.po.keys.RelationOriginKeyPO;
import org.datasphere.mdm.data.type.data.RelationType;
import org.postgresql.util.PGobject;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueTokenizer;
/**
 * Complex objects tokenizer.
 * @author Mikhail Mikhailov
 */
public class RelationKeysRowTokenizer extends AbstractKeysRowTokenizer<RelationKeysPO> {
    /**
     * Default tokenizer.
     */
    public static final RelationKeysRowTokenizer DEFAULT_RELATION_KEYS_TOKENIZER
        = new RelationKeysRowTokenizer();
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the declaration.
     * from_key record_key,
    to_key record_key,
     */
    enum RelationKeysFields {
        RELATION_TYPE((v, po) -> po.setRelationType(RelationType.valueOf(v))),
        // PG struct
        FROM_KEY((v, po) -> {

            if (StringUtils.isBlank(v)) {
                return;
            }

            RecordKeysPO from = new RecordKeysPO();
            from.setId(v);

            po.setFromKeys(from);

            // Disabled until FPD works.
            /*
            PGtokenizer tk = new PGtokenizer(PGtokenizer.removePara(VendorUtils.stripSL(v, '"')), ',');
            if (tk.getSize() == 0) {
                return;
            }

            RecordKeysPO from = RecordKeysRowTokenizer.DEFAULT_RECORD_KEYS_TOKENIZER.process(tk);
            if (Objects.nonNull(from)) {
                po.setFromKeys(from);
            }
            */
        }),
        // PG struct
        TO_KEY((v, po) -> {

            if (StringUtils.isBlank(v)) {
                return;
            }

            RecordKeysPO to = new RecordKeysPO();
            to.setId(v);

            po.setToKeys(to);
            /*
            PGtokenizer tk = new PGtokenizer(PGtokenizer.removePara(VendorUtils.stripSL(v, '"')), ',');
            if (tk.getSize() == 0) {
                return;
            }

            RecordKeysPO to = RecordKeysRowTokenizer.DEFAULT_RECORD_KEYS_TOKENIZER.process(tk);
            if (Objects.nonNull(to)) {
                po.setToKeys(to);
            }
            */
        }),
        // PG array
        ORIGIN_KEYS((v, po) -> {

            CompositeValueTokenizer cvt = CompositeValueTokenizer.arrayTokenizer(v);
            if (cvt.getSize() == 0) {
                return;
            }

            List<RelationOriginKeyPO> originKeys = new ArrayList<>();
            for (int i = 0; i < cvt.getSize(); i++) {
                try {

                    PGobject obj = new PGobject();
                    obj.setValue(cvt.getToken(i));

                    RelationOriginKeyPO parsed
                        = RelationOriginKeyRowTokenizer
                            .DEFAULT_RELATION_ORIGIN_KEY_TOKENIZER.process(obj);

                    if (Objects.nonNull(parsed)) {
                        originKeys.add(parsed);
                    }
                } catch (SQLException sqle) {
                    // Can't happen. Exception defined for overriding
                }
            }

            po.setOriginKeys(originKeys);
        });

        RelationKeysFields(BiConsumer<String, RelationKeysPO> f) {
            this.converter = f;
        }

        private BiConsumer<String, RelationKeysPO> converter;
        public BiConsumer<String, RelationKeysPO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    public RelationKeysRowTokenizer() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return super.size() + RelationKeysFields.values().length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RelationKeysPO process(CompositeValueTokenizer fields) {

        int sz = size();
        if (fields.getSize() != sz) {
            return null;
        }

        CompositeValueIterator tki = new CompositeValueIterator(fields);
        RelationKeysPO po = new RelationKeysPO();
        super.process(tki, po);

        for (int i = 0; i < RelationKeysFields.values().length && tki.hasNext(); i++) {

            String token = tki.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            RelationKeysFields.values()[i].consumer().accept(token, po);
        }

        return po;
    }


}
