

package org.datasphere.mdm.core.type.data.impl;

import java.util.Objects;

import org.datasphere.mdm.core.type.data.CodeLinkValue;

/**
 * @author Mikhail Mikhailov
 * String simple attribute.
 */
public class StringSimpleAttributeImpl extends AbstractSimpleAttribute<String> implements CodeLinkValue {
    /**
     * Link to record of the code attribute.
     */
    private String linkEtalonId;
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected StringSimpleAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name the name of the attribute
     */
    public StringSimpleAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name the name
     * @param value the value
     */
    public StringSimpleAttributeImpl(String name, String value) {
        super(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleDataType getDataType() {
        return SimpleDataType.STRING;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public StringSimpleAttributeImpl withValue(String value) {
        setValue(value);
        return this;
    }

    /**
     * @return the linkEtalonId
     */
    @Override
    public String getLinkEtalonId() {
        return linkEtalonId;
    }

    /**
     * @param linkEtalonId the linkEtalonId to set
     */
    @Override
    public void setLinkEtalonId(String linkEtalonId) {
        this.linkEtalonId = linkEtalonId;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(getValue()) || getValue().isEmpty();
    }
}
