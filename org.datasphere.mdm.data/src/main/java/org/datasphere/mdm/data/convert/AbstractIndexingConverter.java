

package org.datasphere.mdm.data.convert;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.datasphere.mdm.core.type.data.ArrayAttribute;
import org.datasphere.mdm.core.type.data.Attribute;
import org.datasphere.mdm.core.type.data.CodeAttribute;
import org.datasphere.mdm.core.type.data.ComplexAttribute;
import org.datasphere.mdm.core.type.data.DataRecord;
import org.datasphere.mdm.core.type.data.LargeValue;
import org.datasphere.mdm.core.type.data.MeasuredValue;
import org.datasphere.mdm.core.type.data.SimpleAttribute;
import org.datasphere.mdm.core.type.data.impl.DateArrayValue;
import org.datasphere.mdm.core.type.data.impl.IntegerArrayValue;
import org.datasphere.mdm.core.type.data.impl.NumberArrayValue;
import org.datasphere.mdm.core.type.data.impl.StringArrayValue;
import org.datasphere.mdm.core.type.data.impl.TimeArrayValue;
import org.datasphere.mdm.core.type.data.impl.TimestampArrayValue;
import org.datasphere.mdm.search.type.indexing.IndexingField;
import org.datasphere.mdm.search.type.indexing.impl.IndexingRecordImpl;

/**
 * @author Mikhail Mikhailov on Oct 12, 2019
 * Abstract, common to all data kinds, 'DataRecord' part of the indexing support.
 */
public abstract class AbstractIndexingConverter {
    /**
     * Constructor.
     */
    protected AbstractIndexingConverter() {
        super();
    }

    /**
     * Builds JSON representation of an object for insert or update.
     *
     * @param builder the builder
     * @param record the record
     * @param recordPath current path
     * @throws IOException
     */
    protected static List<IndexingField> buildRecord(DataRecord record) {

        List<IndexingField> collected = new ArrayList<>(record.getSize());
        for (Attribute attr : record.getAttributeValues()) {
            if (attr == null) {
                continue;
            }

            IndexingField retval = buildAttribute(attr);
            if (Objects.nonNull(retval)) {
                collected.add(retval);
            }
        }

        return collected;
    }

    /**
     * Builds JSON representation of an attribute for insert or update.
     *
     * @param builder the builder
     * @param attr the attr
     * @param attrPath current path
     * @throws IOException
     */
    protected static IndexingField buildAttribute(Attribute attr) {

        IndexingField result = null;
        final String name = attr.getName();
        switch (attr.getAttributeType()) {
            case SIMPLE:

                SimpleAttribute<?> simple = (SimpleAttribute<?>) attr;
                switch (simple.getDataType()) {
                    case STRING:
                    case LINK:
                    case ENUM:
                    case DICTIONARY:
                        result = IndexingField.of(name, simple.<String>castValue());
                        break;
                    case NUMBER:
                        result = IndexingField.of(name, simple.<Double>castValue());
                        break;
                    case MEASURED:
                        result = IndexingField.of(name, simple.<MeasuredValue>castValue().getBaseValue());
                        break;
                    case BOOLEAN:
                        result = IndexingField.of(name, simple.<Boolean>castValue());
                        break;
                    case DATE:
                        result = IndexingField.of(name, simple.<LocalDate>castValue());
                        break;
                    case TIME:
                        result = IndexingField.of(name, simple.<LocalTime>castValue());
                        break;
                    case TIMESTAMP:
                        result = IndexingField.of(name, simple.<LocalDateTime>castValue());
                        break;
                    case INTEGER:
                        result = IndexingField.of(name, simple.<Long>castValue());
                        break;
                    case BLOB:
                    case CLOB:
                        LargeValue largeValue = simple.castValue();
                        result = IndexingField.of(name, largeValue == null ? null : largeValue.getFileName());
                        break;
                }
                break;
            case CODE:

                CodeAttribute<?> code = (CodeAttribute<?>) attr;
                switch (code.getDataType()) {
                case INTEGER:
                    result = IndexingField.ofIntegers(name, Stream.concat(Stream.of(code.<Long>castValue()), code.<Long>castSupplementary().stream())
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));
                    break;
                case STRING:
                    result = IndexingField.ofStrings(name, Stream.concat(Stream.of(code.<String>castValue()), code.<String>castSupplementary().stream())
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));
                    break;
                default:
                    break;
                }
                break;
            case ARRAY:

                ArrayAttribute<?> array = (ArrayAttribute<?>) attr;
                if (array.isEmpty()) {
                    break;
                }

                switch (array.getDataType()) {
                    case DICTIONARY:
                    case STRING:
                        result = IndexingField.ofStrings(name, array.getValue().stream()
                            .map(value -> ((StringArrayValue) value).getValue())
                            .collect(Collectors.toList()));
                        break;
                    case NUMBER:
                        result = IndexingField.ofNumbers(name, array.getValue().stream()
                            .map(value -> ((NumberArrayValue) value).getValue())
                            .collect(Collectors.toList()));
                        break;
                    case INTEGER:
                        result = IndexingField.ofIntegers(name, array.getValue().stream()
                            .map(value -> ((IntegerArrayValue) value).getValue())
                            .collect(Collectors.toList()));
                        break;
                    case DATE:
                        result = IndexingField.ofDates(name, array.getValue().stream()
                            .map(value -> ((DateArrayValue) value).getValue())
                            .collect(Collectors.toList()));
                        break;
                    case TIME:
                        result = IndexingField.ofTimes(name, array.getValue().stream()
                            .map(value -> ((TimeArrayValue) value).getValue())
                            .collect(Collectors.toList()));
                        break;
                    case TIMESTAMP:
                        result = IndexingField.ofTimestamps(name, array.getValue().stream()
                            .map(value -> ((TimestampArrayValue) value).getValue())
                            .collect(Collectors.toList()));
                        break;
                }
                break;
            case COMPLEX:

                ComplexAttribute complexAttribute = (ComplexAttribute) attr;
                if (complexAttribute.isEmpty()) {
                    break;
                }

                result = IndexingField.ofRecords(name, complexAttribute.stream()
                    .filter(Objects::nonNull)
                    .map(AbstractIndexingConverter::buildRecord)
                    .map(IndexingRecordImpl::new)
                    .collect(Collectors.toList()));
                break;
        }

        return result;
    }

}
