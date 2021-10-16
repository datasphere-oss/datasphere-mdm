

package org.datasphere.mdm.core.type.data;

import java.util.List;

import org.datasphere.mdm.core.type.model.AttributeElement.AttributeValueType;
import org.datasphere.mdm.search.type.FieldType;

/**
 * @author Mikhail Mikhailov
 * Code attribute values.
 */
public interface CodeAttribute<T> extends SingleValueAttribute<T> {
    /**
     * @author Mikhail Mikhailov
     * Denotes type of the contained data.
     */
    enum CodeDataType {
        /**
         * The string type.
         */
        STRING,
        /**
         * The integer type (long 8 bytes).
         */
        INTEGER;
        /**
         * Converts self to search type.
         * @return self as search type
         */
        public FieldType toSearchType() {

            switch (this) {
            case STRING:
                return FieldType.STRING;
            case INTEGER:
                return FieldType.INTEGER;
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
            case STRING:
                return AttributeValueType.STRING;
            case INTEGER:
                return AttributeValueType.INTEGER;
            default:
                break;
            }

            return null;
        }
        /**
         * Converts model type to self .
         * @return model type as self
         */
        public static CodeDataType fromModelType(AttributeValueType type) {

            switch (type) {
            case STRING:
                return CodeDataType.STRING;
            case INTEGER:
                return CodeDataType.INTEGER;
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
        return AttributeType.CODE;
    }
    /**
     * Gets type of contained data.
     * @return type
     */
    CodeDataType getDataType();

    /**
     * Gets contained supplementary values.
     * @return values.
     */
    List<T> getSupplementary();
    /**
     * Gets contained supplementary values.
     * @return values.
     */
    @SuppressWarnings("unchecked")
    default<V> List<V> castSupplementary() {
        return (List<V>) getSupplementary();
    }
    /**
     * Sets the supplementary values.
     * @param value to set
     */
    void setSupplementary(List<T> value);
    /**
     * Tells, whether this attribute has supplementary values set.
     * @return true, if so, false otherwise
     */
    default boolean hasSupplementary() {
        return getSupplementary() != null && !getSupplementary().isEmpty();
    }
}
