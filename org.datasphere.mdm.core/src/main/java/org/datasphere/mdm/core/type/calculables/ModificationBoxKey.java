

package org.datasphere.mdm.core.type.calculables;

/**
 * @author Mikhail Mikhailov
 * Marks objects, capable to generate box keys.
 */
public interface ModificationBoxKey {
    /**
     * Returns the box key for this object.
     * @return box key
     */
    String toBoxKey();
}
