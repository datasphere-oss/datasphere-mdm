

package org.datasphere.mdm.core.type.data.extended;

import org.datasphere.mdm.core.type.data.impl.IntegerArrayAttributeImpl;

/**
 * @author Dmitry Kopin
 * Extended Integer array attribute.
 */
public class ExtendedIntegerArrayAttributeImpl extends IntegerArrayAttributeImpl
        implements WinnerInformationArrayAttribute<Long> {

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
    public ExtendedIntegerArrayAttributeImpl(String name, String sourceSystem, String externalId) {
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
