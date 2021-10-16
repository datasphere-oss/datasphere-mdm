
package org.datasphere.mdm.core.type.data.impl;

import org.datasphere.mdm.core.type.data.CodeLinkValue;

/**
 * @author Mikhail Mikhailov
 * String array value.
 */
public class StringArrayValue extends AbstractArrayValue<String> implements CodeLinkValue {

    /**
     * Link etalon id.
     */
    private String linkEtalonId;
    /**
     * Constructor.
     */
    public StringArrayValue() {
        super();
    }
    /**
     * Constructor.
     * @param value
     */
    public StringArrayValue(String value) {
        super(value);
    }
    /**
     * Constructor.
     * @param value
     * @param displayValue
     */
    public StringArrayValue(String value, String displayValue) {
        super(value, displayValue);
    }
    /**
     * Constructor.
     * @param value the value
     * @param displayValue the display value
     * @param linkEtalonId link etalon id
     */
    public StringArrayValue(String value, String displayValue, String linkEtalonId) {
        super(value, displayValue);
        this.linkEtalonId = linkEtalonId;
    }
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
