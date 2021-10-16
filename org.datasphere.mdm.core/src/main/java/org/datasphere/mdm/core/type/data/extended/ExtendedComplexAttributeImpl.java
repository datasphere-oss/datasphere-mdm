

package org.datasphere.mdm.core.type.data.extended;

import org.datasphere.mdm.core.type.data.impl.ComplexAttributeImpl;

/**
 * @author Dmitry Kopin
 * Extended Complex attribute.
 */
public class ExtendedComplexAttributeImpl extends ComplexAttributeImpl
        implements WinnerInformationComplexAttribute {

    /**
     * Winner source system
     */
    private final String winnerSourceSystem;
    /**
     * Winner external id
     */
    private final String winnerExternalId;
    /**
     * Constructor.
     */
    public ExtendedComplexAttributeImpl(String name, String sourceSystem, String externalId) {
        super(name);
        this.winnerSourceSystem = sourceSystem;
        this.winnerExternalId = externalId;
    }

    @Override
    public String getWinnerSourceSystem(){
        return winnerSourceSystem;
    }

    @Override
    public String getWinnerExternalId(){
        return winnerExternalId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
