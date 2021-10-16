

package org.datasphere.mdm.core.type.data.extended;

import org.datasphere.mdm.core.type.data.ComplexAttribute;


/**
 * @author Dmitrii Kopin
 * Complex attribute, which can verify itself.
 */
public interface WinnerInformationComplexAttribute extends ComplexAttribute {
    /**
     * get winner source system
     * @return winner source system
     */
    String getWinnerSourceSystem();
    /**
     * get winner external identificator
     * @return winner external identificator
     */
    String getWinnerExternalId();
}
