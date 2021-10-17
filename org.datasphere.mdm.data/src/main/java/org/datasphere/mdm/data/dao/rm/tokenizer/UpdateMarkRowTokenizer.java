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
