

package org.datasphere.mdm.core.type.data.impl;

import org.datasphere.mdm.core.type.data.CodeLinkValue;

/**
 * @author Mikhail Mikhailov
 * Long simple attribute.
 */
public class IntegerSimpleAttributeImpl extends AbstractSimpleAttribute<Long> implements CodeLinkValue {

    /**
     * Link to record of the code attribute.
     */
    private String linkEtalonId;
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected IntegerSimpleAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name
     */
    public IntegerSimpleAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name the name
     * @param value the value
     */
    public IntegerSimpleAttributeImpl(String name, Long value) {
        super(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleDataType getDataType() {
        return SimpleDataType.INTEGER;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public IntegerSimpleAttributeImpl withValue(Long value) {
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
}
