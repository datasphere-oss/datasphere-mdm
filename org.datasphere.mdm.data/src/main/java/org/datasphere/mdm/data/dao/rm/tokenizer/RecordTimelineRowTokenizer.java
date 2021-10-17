

package org.datasphere.mdm.data.dao.rm.tokenizer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.data.RecordTimelinePO;
import org.datasphere.mdm.data.po.data.RecordVistoryPO;
import org.datasphere.mdm.data.po.keys.RecordKeysPO;
import org.postgresql.util.PGobject;
import org.datasphere.mdm.core.dao.tokenizer.AbstractRowTokenizer;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueTokenizer;

/**
 * @author Mikhail Mikhailov
 * Record timeline row tokenizer.
 */
public class RecordTimelineRowTokenizer extends AbstractRowTokenizer<RecordTimelinePO> {
    /**
     * Default tokenizer.
     */
    public static final RecordTimelineRowTokenizer DEFAULT_RECORD_TIMELINE_TOKENIZER
        = new RecordTimelineRowTokenizer();
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the declaration.
     */
    enum RecordTimelineFields {
        RECORD_KEYS((v, po) -> {

            if (StringUtils.isBlank(v)) {
                return;
            }

            try {

                PGobject obj = new PGobject();
                obj.setValue(v);

                RecordKeysPO parsed
                    = RecordKeysRowTokenizer
                        .DEFAULT_RECORD_KEYS_TOKENIZER.process(obj);

                if (Objects.isNull(parsed)) {
                    return;
                }

                po.setKeys(parsed);
            } catch (SQLException sqle) {
                // Can't happen. Exception defined for overriding
            }
        }),
        RECORD_VISTORY((v, po) -> {

            if (StringUtils.isBlank(v)) {
                return;
            }

            CompositeValueTokenizer cvt = CompositeValueTokenizer.arrayTokenizer(v);
            List<RecordVistoryPO> result = new ArrayList<>(cvt.getSize());
            for (int i = 0; i < cvt.getSize(); i++) {

                try {

                    PGobject obj = new PGobject();
                    obj.setValue(cvt.getToken(i));

                    RecordVistoryPO parsed = RecordVistoryRowTokenizer.DEFAULT_RECORD_VISTORY_TOKENIZER.process(obj);
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

        RecordTimelineFields(BiConsumer<String, RecordTimelinePO> f) {
            this.converter = f;
        }

        private BiConsumer<String, RecordTimelinePO> converter;
        public BiConsumer<String, RecordTimelinePO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    public RecordTimelineRowTokenizer() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return super.size() + RecordTimelineFields.values().length;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected RecordTimelinePO process(CompositeValueTokenizer fields) {

        int sz = size();
        if (fields.getSize() != sz) {
            return null;
        }

        CompositeValueIterator tki = new CompositeValueIterator(fields);
        RecordTimelinePO po = new RecordTimelinePO();

        for (int i = 0; i < RecordTimelineFields.values().length && tki.hasNext(); i++) {

            String token = tki.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            RecordTimelineFields.values()[i].consumer().accept(token, po);
        }

        return po;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(CompositeValueIterator tk, RecordTimelinePO t) {
        // Nothing. Has no parents
    }

}
