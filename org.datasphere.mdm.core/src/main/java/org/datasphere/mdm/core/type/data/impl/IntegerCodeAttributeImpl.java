

package org.datasphere.mdm.core.type.data.impl;

import java.util.List;

/**
 * @author Mikhail Mikhailov
 * Integer code attribute.
 */
public class IntegerCodeAttributeImpl extends AbstractCodeAttribute<Long> {
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected IntegerCodeAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name the name of the attribute
     */
    public IntegerCodeAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name the name
     * @param value the value
     */
    public IntegerCodeAttributeImpl(String name, Long value) {
        super(name, value);
    }

    /**
     * Constructor.
     * @param name
     */
    protected IntegerCodeAttributeImpl(String name, Long value, List<Long> supplementary) {
        super(name, value, supplementary);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CodeDataType getDataType() {
        return CodeDataType.INTEGER;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public IntegerCodeAttributeImpl withValue(Long value) {
        setValue(value);
        return this;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public IntegerCodeAttributeImpl withSupplementary(List<Long> value) {
        setSupplementary(value);
        return this;
    }
}
