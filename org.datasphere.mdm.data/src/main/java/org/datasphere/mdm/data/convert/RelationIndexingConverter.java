

package org.datasphere.mdm.data.convert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.type.data.RecordStatus;
import org.datasphere.mdm.data.type.data.EtalonRelation;
import org.datasphere.mdm.data.type.data.EtalonRelationInfoSection;
import org.datasphere.mdm.data.type.keys.RelationKeys;
import org.datasphere.mdm.meta.type.RelativeDirection;
import org.datasphere.mdm.meta.type.search.EntityIndexType;
import org.datasphere.mdm.meta.type.search.RelationFromIndexId;
import org.datasphere.mdm.meta.type.search.RelationHeaderField;
import org.datasphere.mdm.meta.type.search.RelationToIndexId;
import org.datasphere.mdm.search.type.id.AbstractManagedIndexId;
import org.datasphere.mdm.search.type.indexing.Indexing;
import org.datasphere.mdm.search.type.indexing.IndexingField;

/**
 * @author Mikhail Mikhailov on Oct 12, 2019
 * Relations
 */
public final class RelationIndexingConverter extends AbstractIndexingConverter {

    /**
     * Constructor.
     */
    private RelationIndexingConverter() {
        super();
    }

    public static List<Indexing> convert(RelationKeys keys, Collection<EtalonRelation> relations) {
        return convert(keys, relations, null);
    }

    public static List<Indexing> convert(RelationKeys keys, Collection<EtalonRelation> relations, RelativeDirection processing) {

        if (CollectionUtils.isEmpty(relations)) {
            return Collections.emptyList();
        }

        List<Indexing> result = new ArrayList<>(relations.size() * 2);
        for (EtalonRelation relation : relations) {

            EtalonRelationInfoSection infoSection = relation.getInfoSection();
            List<IndexingField> fields = new ArrayList<>(RelationHeaderField.values().length + relation.getSize());

            // 1. Common
            fields.add(IndexingField.of(RelationHeaderField.FIELD_RELATION_NAME.getName(), infoSection.getRelationName()));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_RELATION_TYPE.getName(), infoSection.getRelationType().name()));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_ETALON_ID.getName(), infoSection.getRelationEtalonKey()));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_FROM_ETALON_ID.getName(), infoSection.getFromEtalonKey().getId()));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_TO_ETALON_ID.getName(), infoSection.getToEtalonKey().getId()));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_PERIOD_ID.getName(), AbstractManagedIndexId.periodIdValToString(infoSection.getValidTo())));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_DELETED.getName(), keys.getEtalonKey().getStatus() == RecordStatus.INACTIVE));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_INACTIVE.getName(), infoSection.getStatus() == RecordStatus.INACTIVE));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_ORIGINATOR.getName(), infoSection.getUpdatedBy()));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_OPERATION_TYPE.getName(), infoSection.getOperationType().name()));

            fields.add(IndexingField.of(RelationHeaderField.FIELD_FROM.getName(), infoSection.getValidFrom()));
            fields.add(IndexingField.of(RelationHeaderField.FIELD_TO.getName(), infoSection.getValidTo()));

            fields.add(IndexingField.of(RelationHeaderField.FIELD_CREATED_AT.getName(), infoSection.getCreateDate()));
            if (infoSection.getUpdateDate() != null) {
                fields.add(IndexingField.of(RelationHeaderField.FIELD_UPDATED_AT.getName(), infoSection.getUpdateDate()));
            }

            fields.add(IndexingField.of(RelationHeaderField.FIELD_CREATED_BY.getName(), infoSection.getCreatedBy()));
            if (infoSection.getUpdatedBy() != null) {
                fields.add(IndexingField.of(RelationHeaderField.FIELD_UPDATED_BY.getName(), infoSection.getUpdatedBy()));
            }

            // 1.1. save relation attributes data
            if (relation.getSize() > 0) {
                fields.addAll(AbstractIndexingConverter.buildRecord(relation));
            }

            // 2. Append
            append(result, fields, infoSection, processing);
        }

        return result;
    }

    private static void append(List<Indexing> result, List<IndexingField> fields, EtalonRelationInfoSection infoSection,  RelativeDirection processing) {

        // 1. From
        if (processing == null || processing == RelativeDirection.FROM) {

            List<IndexingField> fromFields = new ArrayList<>(fields);
            fromFields.add(IndexingField.of(RelationHeaderField.FIELD_DIRECTION_FROM.getName(), true));

            result.add(new Indexing(EntityIndexType.RELATION,
                    RelationFromIndexId.of(
                            infoSection.getFromEntityName(),
                            infoSection.getRelationName(),
                            infoSection.getFromEtalonKey().getId(),
                            infoSection.getToEtalonKey().getId(),
                            infoSection.getValidTo()))
                        .withFields(fromFields));
        }

        // 2. To
        if (processing == null || processing == RelativeDirection.TO) {

            List<IndexingField> toFields = new ArrayList<>(fields);
            toFields.add(IndexingField.of(RelationHeaderField.FIELD_DIRECTION_FROM.getName(), false));

            result.add(new Indexing(EntityIndexType.RELATION,
                    RelationToIndexId.of(
                            infoSection.getToEntityName(),
                            infoSection.getRelationName(),
                            infoSection.getFromEtalonKey().getId(),
                            infoSection.getToEtalonKey().getId(),
                            infoSection.getValidTo()))
                        .withFields(toFields));
        }
    }
}
