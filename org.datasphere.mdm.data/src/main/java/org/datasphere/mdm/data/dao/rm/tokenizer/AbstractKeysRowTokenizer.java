/*
 * Unidata Platform Community Edition
 * Copyright (c) 2013-2020, UNIDATA LLC, All rights reserved.
 * This file is part of the Unidata Platform Community Edition software.
 * 
 * Unidata Platform Community Edition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Unidata Platform Community Edition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
