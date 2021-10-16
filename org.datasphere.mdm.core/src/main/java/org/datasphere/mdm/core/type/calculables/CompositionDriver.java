

package org.datasphere.mdm.core.type.calculables;

import org.datasphere.mdm.core.type.data.DataRecord;

/**
 * @author mikhail
 * Type of calculable, held by the holder.
 */
public interface CompositionDriver<T extends Calculable, A extends BvrCalculationInfo<T>, B extends BvtCalculationInfo<T>> {
    /**
     * Gets the tag name of this type.
     * @return the type name
     */
    String getName();
    /**
     * Gets the basic description of this type.
     * @return decsription
     */
    String getDescription();
    /**
     * Tells whether the given calculable set denotes an active interval (validity range).
     * @param info calculables info
     * @return true, if active, false otherwise
     */
    boolean hasActiveBVR(A info);
    /**
     * Performs BVR calculation on this type
     * @param info the calculables info
     * @return BVR winner
     */
    T toBVR(A info);
    /**
     * Performs BVT calculation on this type
     * @param info the calculables info
     * @return BVT winner record
     */
    DataRecord toBVT(B info);
}
