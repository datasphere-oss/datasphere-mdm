

package org.datasphere.mdm.core.type.data.extended;

import java.time.LocalDate;

import org.datasphere.mdm.core.type.data.impl.DateArrayAttributeImpl;

/**
 * @author Dmitry Kopin
 * Extended Date array attribute.
 */
public class ExtendedDateArrayAttributeImpl extends DateArrayAttributeImpl
        implements WinnerInformationArrayAttribute<LocalDate> {

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
    public ExtendedDateArrayAttributeImpl(String name, String sourceSystem, String externalId) {
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
