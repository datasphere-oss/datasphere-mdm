
package org.datasphere.mdm.core.type.model;

import java.util.Collection;

import javax.annotation.Nullable;

/**
 * @author Mikhail Mikhailov on Oct 6, 2020
 * Enumeration view.
 */
public interface EnumerationElement extends IdentityElement, NamedDisplayableElement, CustomPropertiesElement {
    /**
     * Gets all enumeration values.
     * @return values
     */
    Collection<NamedDisplayableElement> getEnumerationValues();
    /**
     * Returns true, if enumeration contains named element.
     * @return true, if enumeration contains named element.
     */
    boolean valueExists(String name);
    /**
     * Gets enumeration value with given name.
     * @param name the name
     * @return value
     */
    @Nullable
    NamedDisplayableElement getEnumerationValue(String name);
}
