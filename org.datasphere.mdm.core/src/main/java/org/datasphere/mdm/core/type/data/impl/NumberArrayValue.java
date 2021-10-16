
package org.datasphere.mdm.core.type.data.impl;

/**
 * @author Mikhail Mikhailov
 * Double array value
 */
public class NumberArrayValue extends AbstractArrayValue<Double> {

    /**
     * Constructor.
     */
    public NumberArrayValue() {
        super();
    }

    /**
     * Constructor.
     * @param value
     * @param displayValue
     */
    public NumberArrayValue(Double value, String displayValue) {
        super(value, displayValue);
    }

    /**
     * Constructor.
     * @param value
     */
    public NumberArrayValue(Double value) {
        super(value);
    }}
