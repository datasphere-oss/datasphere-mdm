

package org.datasphere.mdm.data.dao.rm.tokenizer;

import java.util.function.BiConsumer;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.core.dao.tokenizer.AbstractRowTokenizer;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.RowTokenizerFields;
import org.datasphere.mdm.core.dao.vendor.VendorUtils;
import org.datasphere.mdm.core.po.AbstractObjectPO;

/**
 * @author Mikhail Mikhailov
 * Create/update mark holder PO tokenizer.
 */
public abstract class UpdateMarkRowTokenizer<T extends AbstractObjectPO> extends AbstractRowTokenizer<T> {
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the declaration.
     */
    enum UpdateMarkField implements RowTokenizerFields<AbstractObjectPO> {
        CREATE_DATE((v, po) -> po.setCreateDate(VendorUtils.stringToTimestamp(v))),
        CREATED_BY((v, po) -> po.setCreatedBy(v)),
        UPDATE_DATE((v, po) -> po.setUpdateDate(VendorUtils.stringToTimestamp(v))),
        UPDATED_BY((v, po) -> po.setUpdatedBy(v));
        /**
         * Constructor.
         * @param f the consumer
         */
        UpdateMarkField(BiConsumer<String, AbstractObjectPO> f) {
            this.converter = f;
        }
        /**
         * Converter func.
         */
        private BiConsumer<String, AbstractObjectPO> converter;
        /**
         * {@inheritDoc}
         */
        @Override
        public BiConsumer<String, AbstractObjectPO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    protected UpdateMarkRowTokenizer() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return UpdateMarkField.values().length + super.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(CompositeValueIterator rti, T po) {

        for (int i = 0; i < UpdateMarkField.values().length && rti.hasNext(); i++) {

            String token = rti.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            UpdateMarkField.values()[i].consumer().accept(token, po);
        }
    }
}
