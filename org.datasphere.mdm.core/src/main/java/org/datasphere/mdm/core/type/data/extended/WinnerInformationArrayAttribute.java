

package org.datasphere.mdm.core.type.data.extended;

import org.datasphere.mdm.core.type.data.ArrayAttribute;


/**
 * @author Dmitrii Kopin
 * Array attribute, which contains information about winner source.
 */
public interface WinnerInformationArrayAttribute<T> extends ArrayAttribute<T> {
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
