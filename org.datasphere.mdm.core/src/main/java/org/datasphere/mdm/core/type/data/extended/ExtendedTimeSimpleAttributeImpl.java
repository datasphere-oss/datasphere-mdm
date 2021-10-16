

package org.datasphere.mdm.core.type.data.extended;

import java.time.LocalTime;

import org.datasphere.mdm.core.type.data.impl.TimeSimpleAttributeImpl;

/**
 * @author Dmitry Kopin
 * Extended Time attribute.
 */
public class ExtendedTimeSimpleAttributeImpl extends TimeSimpleAttributeImpl
        implements WinnerInformationSimpleAttribute<LocalTime> {

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
    public ExtendedTimeSimpleAttributeImpl(String name, LocalTime value, String sourceSystem, String externalId) {
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
