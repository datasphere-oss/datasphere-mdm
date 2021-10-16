

package org.datasphere.mdm.core.type.model;

import java.util.Collection;

import javax.annotation.Nullable;

/**
 * @author Mikhail Mikhailov on Nov 5, 2020
 * The measurement category.
 */
public interface MeasurementCategoryElement extends IdentityElement, NamedDisplayableElement, CustomPropertiesElement {
    /**
     * Gets all unit values.
     * @return values
     */
    Collection<MeasurementUnitElement> getUnits();
    /**
     * Returns true, if category contains named element.
     * @return true, if category contains named element.
     */
    boolean exists(String name);
    /**
     * Gets unit value with given name.
     * @param name the name
     * @return value
     */
    @Nullable
    MeasurementUnitElement getUnit(String name);
    /**
     * Gets the base unit element.
     * @return base unit element
     */
    MeasurementUnitElement getBaseUnit();
}
