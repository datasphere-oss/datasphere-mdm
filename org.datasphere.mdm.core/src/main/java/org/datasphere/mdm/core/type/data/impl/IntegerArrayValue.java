

package org.datasphere.mdm.core.type.data.impl;

import org.datasphere.mdm.core.type.data.CodeLinkValue;

/**
 * @author Mikhail Mikhailov
 * Long array value.
 */
public class IntegerArrayValue extends AbstractArrayValue<Long> implements CodeLinkValue {

    /**
     * Constructor.
     */
    public IntegerArrayValue() {
        super();
    }
    /**
     * Constructor.
     * @param value
     * @param displayValue
     */
    public IntegerArrayValue(Long value, String displayValue) {
        super(value, displayValue);
    }
    /**
     * Constructor.
     * @param value
     */
    public IntegerArrayValue(Long value) {
        super(value);
    }
    /**
     * Link etalon id.
     */
    private String linkEtalonId;
    /**
     * {@inheritDoc}
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
