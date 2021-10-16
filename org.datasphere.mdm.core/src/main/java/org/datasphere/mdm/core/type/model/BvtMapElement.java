

package org.datasphere.mdm.core.type.model;

import java.util.Map;

/**
 * @author Mikhail Mikhailov
 * Marks elements, capable of holding a BVT map.
 */
public interface BvtMapElement {
    /**
     * Gets the BVT map.
     * @return the map
     */
    Map<String, Map<String, Integer>> getBvtMap();
}
