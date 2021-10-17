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
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueTokenizer;
import org.datasphere.mdm.data.po.keys.RecordOriginKeyPO;

/**
 * @author Mikhail Mikhailov
 * Record origin keyz row tokenizer.
 */
public class RecordOriginKeyRowTokenizer extends AbstractOriginKeyRowTokenizer<RecordOriginKeyPO> {
    /**
     * Default tokenizer.
     */
    public static final RecordOriginKeyRowTokenizer DEFAULT_RECORD_ORIGIN_KEY_TOKENIZER
        = new RecordOriginKeyRowTokenizer();
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the declaration.
     */
    enum RecordOriginKeyFields {
        EXTERNAL_ID((v, po) -> po.setExternalId(v));

        RecordOriginKeyFields(BiConsumer<String, RecordOriginKeyPO> f) {
            this.converter = f;
        }

        private BiConsumer<String, RecordOriginKeyPO> converter;
        public BiConsumer<String, RecordOriginKeyPO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    public RecordOriginKeyRowTokenizer() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return RecordOriginKeyFields.values().length + super.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected RecordOriginKeyPO process(CompositeValueTokenizer fields) {

        int sz = size();
        if (fields.getSize() != sz) {
            return null;
        }

        CompositeValueIterator tk = new CompositeValueIterator(fields);
        RecordOriginKeyPO po = new RecordOriginKeyPO();
        super.process(tk, po);

        for (int i = 0; i < RecordOriginKeyFields.values().length && tk.hasNext(); i++) {

            String token = tk.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            RecordOriginKeyFields.values()[i].consumer().accept(token, po);
        }

        return po;
    }

}
