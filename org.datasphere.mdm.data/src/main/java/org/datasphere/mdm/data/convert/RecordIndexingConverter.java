

package org.datasphere.mdm.data.convert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.datasphere.mdm.data.type.data.EtalonRecord;
import org.datasphere.mdm.data.type.data.EtalonRecordInfoSection;
import org.datasphere.mdm.meta.type.search.EntityIndexType;
import org.datasphere.mdm.meta.type.search.RecordIndexId;
import org.datasphere.mdm.search.type.indexing.Indexing;
import org.datasphere.mdm.search.type.indexing.IndexingField;

/**
 * @author Mikhail Mikhailov on Oct 10, 2019
 */
public final class RecordIndexingConverter extends AbstractIndexingConverter {
    /**
     * Constructor.
     */
    private RecordIndexingConverter() {
        super();
    }

    public static List<Indexing> convert(Map<EtalonRecord, Collection<IndexingField>> records) {

        if (MapUtils.isEmpty(records)) {
            return Collections.emptyList();
        }

        List<Indexing> result = new ArrayList<>(records.size());
        for (Entry<EtalonRecord, Collection<IndexingField>> e : records.entrySet()) {

            EtalonRecord etalonRecord = e.getKey();
            EtalonRecordInfoSection infoSection = etalonRecord.getInfoSection();

            List<IndexingField> fields = new ArrayList<>(e.getValue().size() + etalonRecord.getSize());

            // 1. Header
            fields.addAll(e.getValue());

            // 2. Data
            fields.addAll(AbstractIndexingConverter.buildRecord(etalonRecord));

            // 3. Add
            result.add(new Indexing(EntityIndexType.RECORD,
                    RecordIndexId.of(
                            infoSection.getEntityName(),
                            infoSection.getEtalonKey().getId(),
                            infoSection.getValidTo()))
                        .withFields(fields));
        }

        return result;
    }
}
