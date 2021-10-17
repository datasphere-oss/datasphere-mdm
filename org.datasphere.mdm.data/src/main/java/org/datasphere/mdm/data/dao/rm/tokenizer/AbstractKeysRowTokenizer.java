

package org.datasphere.mdm.data.dao.rm.tokenizer;

import java.util.function.BiConsumer;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.keys.AbstractKeysPO;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
//import org.datasphere.mdm.core.type.data.ApprovalState;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Record origin keyz row tokenizer.
 */
public abstract class AbstractKeysRowTokenizer<T extends AbstractKeysPO> extends UpdateMarkRowTokenizer<T> {
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the declaration.
     */
    enum AbstractKeysFields {
        SHARD((v, po) -> po.setShard(Integer.parseInt(v))),
        LSN((v, po) -> po.setLsn(Long.parseLong(v))),
        ID((v, po) -> po.setId(v)),
        NAME((v, po) -> po.setName(v)),
        STATUS((v, po) -> po.setStatus(RecordStatus.valueOf(v))),
//        STATE((v, po) -> po.setState(ApprovalState.valueOf(v))),
        APPROVED((v, po) -> po.setApproved(BooleanUtils.toBooleanObject(v)));

        AbstractKeysFields(BiConsumer<String, AbstractKeysPO> f) {
            this.converter = f;
        }

        private BiConsumer<String, AbstractKeysPO> converter;
        public BiConsumer<String, AbstractKeysPO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    public AbstractKeysRowTokenizer() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return AbstractKeysFields.values().length + super.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(CompositeValueIterator rti, T po) {

        for (int i = 0; i < AbstractKeysFields.values().length && rti.hasNext(); i++) {

            String token = rti.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            AbstractKeysFields.values()[i].consumer().accept(token, po);
        }

        super.process(rti, po);
    }
}
