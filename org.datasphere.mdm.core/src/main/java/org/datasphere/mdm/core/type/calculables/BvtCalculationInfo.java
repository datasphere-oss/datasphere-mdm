

package org.datasphere.mdm.core.type.calculables;

import java.util.Map;

/**
 * @author Mikhail Mikhailov
 * The BVT calculation info.
 */
public interface BvtCalculationInfo<T extends Calculable> extends CalculationInfo<T> {
    /**
     * Returns the BVT map, where key is the attr path and the value is the source system info
     * @return BVT map
     */
    Map<String, Map<String, Integer>> getBvtMap();
}
