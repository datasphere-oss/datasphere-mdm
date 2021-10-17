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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.keys.RecordKeysPO;
import org.datasphere.mdm.data.po.keys.RecordOriginKeyPO;
import org.postgresql.util.PGobject;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueTokenizer;
/**
 * Complex objects tokenizer.
 * @author Mikhail Mikhailov
 */
public class RecordKeysRowTokenizer extends AbstractKeysRowTokenizer<RecordKeysPO> {
    /**
     * Default tokenizer.
     */
    public static final RecordKeysRowTokenizer DEFAULT_RECORD_KEYS_TOKENIZER
        = new RecordKeysRowTokenizer();
    /**
     * @author Mikhail Mikhailov
     * Fields as they follow in the declaration.
     */
    enum RecordKeysFields {
        ORIGIN_KEYS((v, po) -> {

            if (StringUtils.isBlank(v)) {
                return;
            }

            CompositeValueTokenizer cvt = CompositeValueTokenizer.arrayTokenizer(v);
            List<RecordOriginKeyPO> result = new ArrayList<>(cvt.getSize());
            for (int i = 0; i < cvt.getSize(); i++) {
                try {

                    PGobject obj = new PGobject();
                    obj.setValue(cvt.getToken(i));

                    RecordOriginKeyPO parsed
                        = RecordOriginKeyRowTokenizer
                            .DEFAULT_RECORD_ORIGIN_KEY_TOKENIZER.process(obj);

                    result.add(parsed);
                } catch (SQLException sqle) {
                    // Can't happen. Exception defined for overriding
                }
            }

            if (result.isEmpty()) {
                return;
            }

            po.setOriginKeys(result);
        });

        RecordKeysFields(BiConsumer<String, RecordKeysPO> f) {
            this.converter = f;
        }

        private BiConsumer<String, RecordKeysPO> converter;
        public BiConsumer<String, RecordKeysPO> consumer() {
            return converter;
        }
    }
    /**
     * Constructor.
     */
    public RecordKeysRowTokenizer() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return super.size() + RecordKeysFields.values().length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecordKeysPO process(CompositeValueTokenizer fields) {

        int sz = size();
        if (fields.getSize() != sz) {
            return null;
        }

        CompositeValueIterator tki = new CompositeValueIterator(fields);
        RecordKeysPO po = new RecordKeysPO();
        super.process(tki, po);

        for (int i = 0; i < RecordKeysFields.values().length && tki.hasNext(); i++) {

            String token = tki.next();
            if (StringUtils.isBlank(token)) {
                continue;
            }

            RecordKeysFields.values()[i].consumer().accept(token, po);
        }

        return po;
    }
}
