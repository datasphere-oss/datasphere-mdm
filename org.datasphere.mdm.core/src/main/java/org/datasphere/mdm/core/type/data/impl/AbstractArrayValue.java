

package org.datasphere.mdm.core.type.data.impl;

import java.util.Objects;

import org.datasphere.mdm.core.type.data.ArrayValue;

/**
 * @author Mikhail Mikhailov
 * Array value.
 */
public abstract class AbstractArrayValue<T> implements ArrayValue<T> {
    /**
     * The value
     */
    private T value;
    /**
     * Display value.
     */
    private String displayValue;
    /**
     * Constructor.
     */
    protected AbstractArrayValue() {
        super();
    }
    /**
     * Constructor.
     */
    protected AbstractArrayValue(T value) {
        this();
        this.value = value;
    }
    /**
     * Constructor.
     */
    protected AbstractArrayValue(T value, String displayValue) {
        this(value);
        this.displayValue = displayValue;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayValue() {
        return displayValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Objects.nonNull(value) ? value.toString() : "null";
    }
}
