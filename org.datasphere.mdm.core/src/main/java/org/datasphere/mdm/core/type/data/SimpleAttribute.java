

package org.datasphere.mdm.core.type.data;

import org.datasphere.mdm.core.type.model.AttributeElement.AttributeValueType;
import org.datasphere.mdm.search.type.FieldType;

/**
 * @author Mikhail Mikhailov
 * Simple attribute.
 */
public interface SimpleAttribute<T> extends SingleValueAttribute<T>, DisplayValue {
    /**
     * @author Mikhail Mikhailov
     * Denotes type of the contained data.
     */
    public enum SimpleDataType {
        /**
         * The string type.
         */
        STRING,
        /**
         * Dictionary, as defined in XSD.
         */
        DICTIONARY,
        /**
         * The integer type (long 8 bytes).
         */
        INTEGER,
        /**
         * The floating point type (double 8 bytes).
         */
        NUMBER,
        /**
         * The boolean type.
         */
        BOOLEAN,
        /**
         * Binary large object.
         */
        BLOB,
        /**
         * Character large object.
         */
        CLOB,
        /**
         * The date type.
         */
        DATE,
        /**
         * The time type.
         */
        TIME,
        /**
         * The timestamp type.
         */
        TIMESTAMP,
        /**
         * Link to a enum value.
         */
        ENUM,
        /**
         * Special href template, processed by get post-processor, type.
         */
        LINK,
        /**
         * Special type of number.
         */
        MEASURED;
        /**
         * Converts self to search type.
         * @return self as search type
         */
        public FieldType toSearchType() {

            switch (this) {
            case BLOB: // filename is indexed
            case CLOB: // same as above
            case ENUM:
            case LINK:
            case STRING:
                return FieldType.STRING;
            case BOOLEAN:
                return FieldType.BOOLEAN;
            case DATE:
                return FieldType.DATE;
            case TIME:
                return FieldType.TIME;
            case TIMESTAMP:
                return FieldType.TIMESTAMP;
            case INTEGER:
                return FieldType.INTEGER;
            case MEASURED:
            case NUMBER:
                return FieldType.NUMBER;
            default:
                break;
            }

            return null;
        }
        /**
         * Converts self to model type.
         * @return self as search type
         */
        public AttributeValueType toModelType() {

            switch (this) {
            case BLOB:
                return AttributeValueType.BLOB;
            case CLOB:
                return AttributeValueType.CLOB;
            case ENUM:
            case LINK:
            case STRING:
                return AttributeValueType.STRING;
            case BOOLEAN:
                return AttributeValueType.BOOLEAN;
            case DATE:
                return AttributeValueType.DATE;
            case TIME:
                return AttributeValueType.TIME;
            case TIMESTAMP:
                return AttributeValueType.TIMESTAMP;
            case INTEGER:
                return AttributeValueType.INTEGER;
            case MEASURED:
                return AttributeValueType.MEASURED;
            case NUMBER:
                return AttributeValueType.NUMBER;
            default:
                break;
            }

            return null;
        }
        /**
         * Converts model type to self .
         * @return model type as self
         */
        public static SimpleDataType fromModelType(AttributeValueType type) {

            switch (type) {
            case BLOB:
                return SimpleDataType.BLOB;
            case CLOB:
                return SimpleDataType.CLOB;
            case STRING:
                return SimpleDataType.STRING;
            case BOOLEAN:
                return SimpleDataType.BOOLEAN;
            case DATE:
                return SimpleDataType.DATE;
            case TIME:
                return SimpleDataType.TIME;
            case TIMESTAMP:
                return SimpleDataType.TIMESTAMP;
            case INTEGER:
                return SimpleDataType.INTEGER;
            case MEASURED:
                return SimpleDataType.MEASURED;
            case NUMBER:
                return SimpleDataType.NUMBER;
            default:
                break;
            }

            return null;
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default AttributeType getAttributeType() {
        return AttributeType.SIMPLE;
    }
    /**
     * Gets type of contained data.
     * @return type
     */
    SimpleDataType getDataType();
}
