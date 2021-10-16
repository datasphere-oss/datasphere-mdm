

package org.datasphere.mdm.core.type.data.extended;

import org.datasphere.mdm.core.type.data.impl.IntegerCodeAttributeImpl;

/**
 * @author Dmitry Kopin
 * Extended Integer code attribute.
 */
public class ExtendedIntegerCodeAttributeImpl extends IntegerCodeAttributeImpl
        implements WinnerInformationCodeAttribute<Long> {

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
    public ExtendedIntegerCodeAttributeImpl(String name, Long value, String sourceSystem, String externalId) {
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
