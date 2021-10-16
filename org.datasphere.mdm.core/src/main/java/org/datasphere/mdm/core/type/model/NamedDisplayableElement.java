

package org.datasphere.mdm.core.type.model;

/**
 * @author Mikhail Mikhailov
 * Short info about top level elements.
 */
public interface NamedDisplayableElement {
    /**
     * Gets the entity's name.
     * @return name
     */
    String getName();
    /**
     * Gets the entity's display name.
     * @return display name
     */
    String getDisplayName();
    /**
     * Gets a possibly defined description of the element.
     * @return description or null
     */
    String getDescription();
}
