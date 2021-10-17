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

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.data.po.data.RecordVistoryPO;
import org.datasphere.mdm.data.serialization.DataSerializer;
import org.postgresql.util.PGbytea;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueIterator;
import org.datasphere.mdm.core.dao.tokenizer.CompositeValueTokenizer;
import org.datasphere.mdm.core.dao.tokenizer.RowTokenizerField;

/**
 * @author Mikhail Mikhailov
 * Record vistory tokenizer.
 */
public class RecordVistoryRowTokenizer extends AbstractVistoryRowTokenizer<RecordVistoryPO> {
    /**
     * The default static tokenizer.
     */
    public static final RecordVistoryRowTokenizer DEFAULT_RECORD_VISTORY_TOKENIZER
        = new RecordVistoryRowTokenizer();

    /**
     * Protostuff data.
     */
    private static final RowTokenizerField<RecordVistoryPO> DATA_B = (v, po) -> {

        if (StringUtils.isBlank(v)) {
            return;
        }

        try {
            po.setData(DataSerializer.fromProtostuff(PGbytea.toBytes(CompositeValueTokenizer.stripBytea(v).trim().getBytes())));
        } catch(SQLException sqle) {
            // Just suppress
        }
    };

    private static final RowTokenizerField<RecordVistoryPO> MAJOR = (v, po) -> po.setMajor(Integer.parseInt(v));
    private static final RowTokenizerField<RecordVistoryPO> MINOR = (v, po) -> po.setMinor(Integer.parseInt(v));

//    private static final RowTokenizerField<RecordVistoryPO>[] FIELDS = RowTokenizerField.fields(DATA_A, DATA_B, MAJOR, MINOR);
    private static final RowTokenizerField<RecordVistoryPO>[] FIELDS = RowTokenizerField.fields(DATA_B, MAJOR, MINOR);

    /**
     * Constructor.
     */
    private RecordVistoryRowTokenizer() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected int size() {
        return super.size() + FIELDS.length;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    protected RecordVistoryPO process(CompositeValueTokenizer fields) {

        int sz = size();
        if (fields.getSize() != sz) {
            return null;
        }

        CompositeValueIterator rti = new CompositeValueIterator(fields);
        RecordVistoryPO po = new RecordVistoryPO();
        super.process(rti, po);
        super.process(rti, po, FIELDS);

        return po;
    }
}
