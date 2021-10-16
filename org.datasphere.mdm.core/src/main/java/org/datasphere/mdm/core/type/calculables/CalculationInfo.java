

package org.datasphere.mdm.core.type.calculables;

import java.util.List;

/**
 * @author Mikhail Mikhailov
 * Base interface for supplying calculation infos to the driver.
 */
public interface CalculationInfo<T extends Calculable> {
    /**
     * Returns the versions, suuplied for calculation
     * @return versions
     */
    List<CalculableHolder<T>> getVersions();
}
