

package org.datasphere.mdm.core.type.data.impl;

import java.util.List;

/**
 * @author Mikhail Mikhailov
 * String code attribute.
 */
public class StringCodeAttributeImpl extends AbstractCodeAttribute<String> {
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected StringCodeAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name the name of the attribute
     */
    public StringCodeAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name the name
     * @param value the value
     */
    public StringCodeAttributeImpl(String name, String value) {
        super(name, value);
    }

    /**
     * Constructor.
     * @param name
     */
    protected StringCodeAttributeImpl(String name, String value, List<String> supplementary) {
        super(name, value, supplementary);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CodeDataType getDataType() {
        return CodeDataType.STRING;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public StringCodeAttributeImpl withValue(String value) {
        setValue(value);
        return this;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public StringCodeAttributeImpl withSupplementary(List<String> value) {
        setSupplementary(value);
        return this;
    }
}
