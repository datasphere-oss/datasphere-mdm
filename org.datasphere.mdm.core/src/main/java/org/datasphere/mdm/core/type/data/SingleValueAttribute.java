
package org.datasphere.mdm.core.type.data;

import java.util.Objects;

/**
 * @author Mikhail Mikhailov
 * Single value attribute (code or simple).
 */
public interface SingleValueAttribute<T> extends Attribute {
    /**
     * Gets contained value.
     * @return value.
     */
    T getValue();
    /**
     * Sets the value.
     * @param value to set
     */
    void setValue(T value);
    /**
     * Should not be used without reason.
     * @return cast value
     */
    @SuppressWarnings("unchecked")
    default<V> V castValue() {
        return (V) getValue();
    }
    /**
     * Tries to cast the supplied object value to internal type and sets it.
     * @param o the object.
     */
    @SuppressWarnings("unchecked")
    default void castValue(Object o) {
        setValue((T) o);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default boolean isEmpty() {
        return Objects.isNull(getValue());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default boolean isSingleValue() {
        return true;
    }
}
