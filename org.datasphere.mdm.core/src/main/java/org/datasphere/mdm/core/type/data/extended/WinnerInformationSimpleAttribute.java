

package org.datasphere.mdm.core.type.data.extended;

import org.datasphere.mdm.core.type.data.SimpleAttribute;


/**
 * @author Dmitrii Kopin
 * Simple attribute, which can verify itself.
 */
public interface WinnerInformationSimpleAttribute<T> extends SimpleAttribute<T> {
    /**
     * Default check for simple attributes.
     * @return true, if name is set, false otherwise
     */
    String getWinnerSourceSystem();
    /**
     * get winner external identificator
     * @return winner external identificator
     */
    String getWinnerExternalId();
}
