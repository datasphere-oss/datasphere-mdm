

package org.datasphere.mdm.data.dao.rm.tokenizer;

import java.util.UUID;
import java.util.function.BiConsumer;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.keys.AbstractOriginKeyPO;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.RowTokenizerFields;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Common part of origin keys.
 */
public abstract class AbstractOriginKeyRowTokenizer<T extends AbstractOriginKeyPO> extends UpdateMarkRowTokenizer<T> {
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the declaration.
     */
    enum AbstractOriginKeyFields implements RowTokenizerFields<AbstractOriginKeyPO> {

        ID((v, po) -> po.setId(UUID.fromString(v))),
        INITIAL_OWNER((v, po) -> po.setInitialOwner(UUID.fromString(v))),
        STATUS((v, po) -> po.setStatus(RecordStatus.valueOf(v))),
        ENRICHMENT((v, po) -> po.setEnrichment(BooleanUtils.toBooleanObject(v))),
        REVISION((v, po) -> po.setRevision(Integer.parseInt(v))),
        SOURCE_SYSTEM((v, po) -> po.setSourceSystem(v));

        AbstractOriginKeyFields(BiConsumer<String, AbstractOriginKeyPO> f) {
            this.converter = f;
        }

        private BiConsumer<String, AbstractOriginKeyPO> converter;
        @Override
        public BiConsumer<String, AbstractOriginKeyPO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    protected AbstractOriginKeyRowTokenizer() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return AbstractOriginKeyFields.values().length + super.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(CompositeValueIterator tk, T po) {

        for (int i = 0; i < AbstractOriginKeyFields.values().length && tk.hasNext(); i++) {

            String token = tk.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            AbstractOriginKeyFields.values()[i].consumer().accept(token, po);
        }

        super.process(tk, po);
    }
}
