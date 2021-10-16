

package org.datasphere.mdm.core.type.data.impl;

/**
 * @author Mikhail Mikhailov
 * Contains href template (value) and calculated value (display value) as simple attribute.
 */
public class LinkSimpleAttributeImpl extends AbstractSimpleAttribute<String> {

    /**
     * The display value.
     */
    private String displayValue;
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected LinkSimpleAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name the name
     */
    public LinkSimpleAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name the name
     * @param value the value
     */
    public LinkSimpleAttributeImpl(String name, String value) {
        super(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleDataType getDataType() {
        return SimpleDataType.LINK;
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
    public LinkSimpleAttributeImpl withValue(String value) {
        setValue(value);
        return this;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public LinkSimpleAttributeImpl withDisplayValue(String value) {
        setDisplayValue(value);
        return this;
    }
}
