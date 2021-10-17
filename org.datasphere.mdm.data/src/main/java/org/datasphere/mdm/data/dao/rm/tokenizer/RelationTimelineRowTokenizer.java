

package org.datasphere.mdm.data.dao.rm.tokenizer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.data.RelationTimelinePO;
import org.datasphere.mdm.data.po.data.RelationVistoryPO;
import org.datasphere.mdm.data.po.keys.RelationKeysPO;
import org.postgresql.util.PGobject;
import org.datasphere.mdm.core.dao.tokenizer.AbstractRowTokenizer;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueTokenizer;

/**
 * @author Mikhail Mikhailov
 * Relation timeline row tokenizer.
 */
public class RelationTimelineRowTokenizer extends AbstractRowTokenizer<RelationTimelinePO> {
    /**
     * Default tokenizer.
     */
    public static final RelationTimelineRowTokenizer DEFAULT_RELATION_TIMELINE_TOKENIZER
        = new RelationTimelineRowTokenizer();
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the declaration.
     */
    enum RelationTimelineFields {
        RELATION_KEYS((v, po) -> {

            if (StringUtils.isBlank(v)) {
                return;
            }

            try {

                PGobject obj = new PGobject();
                obj.setValue(v);

                RelationKeysPO parsed
                    = RelationKeysRowTokenizer
                        .DEFAULT_RELATION_KEYS_TOKENIZER.process(obj);

                if (Objects.isNull(parsed)) {
                    return;
                }

                po.setKeys(parsed);
            } catch (SQLException sqle) {
                // Can't happen. Exception defined for overriding
            }
        }),
        RELATION_VISTORY((v, po) -> {

            if (StringUtils.isBlank(v)) {
                return;
            }

            CompositeValueTokenizer cvt = CompositeValueTokenizer.arrayTokenizer(v);
            List<RelationVistoryPO> result = new ArrayList<>(cvt.getSize());
            for (int i = 0; i < cvt.getSize(); i++) {

                try {

                    PGobject obj = new PGobject();
                    obj.setValue(cvt.getToken(i));

                    RelationVistoryPO parsed = RelationVistoryRowTokenizer
                            .DEFAULT_RELATION_VISTORY_TOKENIZER
                                .process(obj);

                    if (Objects.nonNull(parsed)) {
                        result.add(parsed);
                    }
                } catch (SQLException sqle) {
                    // Can't happen. Exception defined for overriding
                }
            }

            if (result.isEmpty()) {
                return;
            }

            po.setVistory(result);
        });

        RelationTimelineFields(BiConsumer<String, RelationTimelinePO> f) {
            this.converter = f;
        }

        private BiConsumer<String, RelationTimelinePO> converter;
        public BiConsumer<String, RelationTimelinePO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    public RelationTimelineRowTokenizer() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return super.size() + RelationTimelineFields.values().length;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected RelationTimelinePO process(CompositeValueTokenizer fields) {

        int sz = size();
        if (fields.getSize() != sz) {
            return null;
        }

        CompositeValueIterator tki = new CompositeValueIterator(fields);
        RelationTimelinePO po = new RelationTimelinePO();

        for (int i = 0; i < RelationTimelineFields.values().length && tki.hasNext(); i++) {

            String token = tki.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            RelationTimelineFields.values()[i].consumer().accept(token, po);
        }

        return po;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(CompositeValueIterator tk, RelationTimelinePO t) {
        // Nothing. Has no parents
    }
}
