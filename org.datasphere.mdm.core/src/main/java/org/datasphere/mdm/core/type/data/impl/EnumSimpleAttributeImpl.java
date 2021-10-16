
package org.datasphere.mdm.core.type.data.impl;

/**
 * @author Mikhail Mikhailov
 * Link to a enum value simple attribute.
 */
public class EnumSimpleAttributeImpl extends AbstractSimpleAttribute<String> {

    /**
     * The display value.
     */
    private String displayValue;
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected EnumSimpleAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name the name
     */
    public EnumSimpleAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name the name
     * @param value the value
     */
    public EnumSimpleAttributeImpl(String name, String value) {
        super(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleDataType getDataType() {
        return SimpleDataType.ENUM;
    }

    /**
     * @return the displayValue
     */
    @Override
    public String getDisplayValue() {
        return displayValue;
    }

    /**
     * @param displayValue the displayValue to set
     */
    @Override
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public EnumSimpleAttributeImpl withValue(String value) {
        setValue(value);
        return this;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public EnumSimpleAttributeImpl withDisplayValue(String value) {
        setDisplayValue(value);
        return this;
    }
}
