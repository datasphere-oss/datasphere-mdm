

package org.datasphere.mdm.data.dao.rm.tokenizer;

import java.util.function.BiConsumer;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.keys.AbstractVistoryPO;
import org.datasphere.mdm.core.dao.tokenizer.AbstractRowTokenizer;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.RowTokenizerFields;
import org.datasphere.mdm.core.dao.vendor.VendorUtils;
import org.datasphere.mdm.core.type.data.DataShift;
import org.datasphere.mdm.core.type.data.OperationType;
import org.datasphere.mdm.core.type.data.RecordStatus;

/**
 * @author Mikhail Mikhailov
 * Vistory tokenizer.
 */
public abstract class AbstractVistoryRowTokenizer<T extends AbstractVistoryPO> extends AbstractRowTokenizer<T> {
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the type declaration.
     */
    enum AbstractVistoryFields implements RowTokenizerFields<AbstractVistoryPO> {

        ID((v, po) -> po.setId(v)),
        ORIGIN_ID((v, po) -> po.setOriginId(v)),
        SHARD((v, po) -> po.setShard(Integer.parseInt(v))),
        REVISION((v, po) -> po.setRevision(Integer.parseInt(v))),
        VALID_FROM((v, po) -> po.setValidFrom(VendorUtils.stringToTimestamp(v))),
        VALID_TO((v, po) -> po.setValidTo(VendorUtils.stringToTimestamp(v))),
        CREATE_DATE((v, po) -> po.setCreateDate(VendorUtils.stringToTimestamp(v))),
        CREATED_BY((v, po) -> po.setCreatedBy(v)),
        STATUS((v, po) -> po.setStatus(RecordStatus.valueOf(v))),
//        APPROVAL((v, po) -> po.setApproval(ApprovalState.valueOf(v))),
        SHIFT((v, po) -> po.setShift(DataShift.valueOf(v))),
        OPERATION_TYPE((v, po) -> po.setOperationType(OperationType.valueOf(v))),
        OPERATION_ID((v, po) -> po.setOperationId(v));

        AbstractVistoryFields(BiConsumer<String, AbstractVistoryPO> f) {
            this.converter = f;
        }

        private BiConsumer<String, AbstractVistoryPO> converter;
        @Override
        public BiConsumer<String, AbstractVistoryPO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    public AbstractVistoryRowTokenizer() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return super.size() + AbstractVistoryFields.values().length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void process(CompositeValueIterator rti, T po) {

        for (int i = 0; i < AbstractVistoryFields.values().length && rti.hasNext(); i++) {

            String token = rti.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            AbstractVistoryFields.values()[i].consumer().accept(token, po);
        }
    }
}
