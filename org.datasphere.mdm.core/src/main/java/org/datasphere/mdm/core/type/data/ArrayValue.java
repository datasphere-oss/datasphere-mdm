

package org.datasphere.mdm.core.type.data;

/**
 * @author Mikhail Mikhailov
 * Array value.
 */
public interface ArrayValue<T> extends DisplayValue {

    /**
     * Gets the value.
     * @return the inner value.
     */
    T getValue();
    /**
     * Sets the value
     * @param value the value to set
     */
    void setValue(T value);
    /**
     * Should not be used without reason.
     * @return
     */
    @SuppressWarnings("unchecked")
    default<V> V castValue() {
        return (V) getValue();
    }
}
