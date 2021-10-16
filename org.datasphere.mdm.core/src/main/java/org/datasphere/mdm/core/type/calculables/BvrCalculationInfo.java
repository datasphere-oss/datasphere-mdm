

package org.datasphere.mdm.core.type.calculables;

import java.util.Map;

/**
 * @author Mikhail Mikhailov
 * The BVR calculation info.
 */
public interface BvrCalculationInfo<T extends Calculable> extends CalculationInfo<T> {
    /**
     * Returns the BVR information map.
     * @return the BVR map
     */
    Map<String, Integer> getBvrMap();
}
