

package org.datasphere.mdm.core.type.data.extended;

import org.datasphere.mdm.core.type.data.impl.NumberSimpleAttributeImpl;

/**
 * @author Dmitry Kopin
 * Extended Number attribute.
 */
public class ExtendedNumberSimpleAttributeImpl extends NumberSimpleAttributeImpl
        implements WinnerInformationSimpleAttribute<Double> {

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
    public ExtendedNumberSimpleAttributeImpl(String name, Double value, String sourceSystem, String externalId) {
        super(name, value);
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
