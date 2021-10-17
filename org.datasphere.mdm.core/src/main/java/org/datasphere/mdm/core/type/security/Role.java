
package org.datasphere.mdm.core.type.security;

import java.io.Serializable;
import java.util.List;

/**
 * @author Denis Kostovarov
 * A security role.
 */
public interface Role extends Serializable {
    /**
     * Gets the name of the role.
     * @return name
     */
    String getName();
    /**
     * Gets the type of the role (system or user defined).
     * @return type
     */
    RoleType getRoleType();
    /**
     * Gets rights, defined for this role.
     * @return rights
     */
    List<Right> getRights();
    /**
     * Gets the display name of the role.
     * @return display name
     */
    String getDisplayName();
    /**
     * Gets the security labels, defined for this role.
     * @return security labels
     */
    List<SecurityLabel> getSecurityLabels();
    /**
     * Gets collection of the custom properties, defined for this user.
     * @return collection of custom properties
     */
    List<CustomProperty> getProperties();

}
