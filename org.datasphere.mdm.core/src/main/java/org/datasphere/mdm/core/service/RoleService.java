

package org.datasphere.mdm.core.service;

import java.util.List;

import org.datasphere.mdm.core.dto.RoleDTO;
import org.datasphere.mdm.core.dto.RolePropertyDTO;
import org.datasphere.mdm.core.dto.SecuredResourceDTO;
import org.datasphere.mdm.core.type.security.CustomProperty;
import org.datasphere.mdm.core.type.security.Role;
import org.datasphere.mdm.core.type.security.SecurityLabel;

public interface RoleService {

    void init();

    /**
     * Gets the role by name.
     *
     * @param roleName
     *            the role name
     * @return the role by name
     */
    Role getRoleByName(String roleName);

    /**
     * Gets the all roles.
     *
     * @return the all roles
     */
    List<Role> getAllRoles();

    /**
     * Gets all roles by user login.
     * @param login the user login
     * @return list of roles
     */
    List<Role> getAllRolesByUserLogin(String login);

    /**
     * Gets the all secured resources.
     *
     * @return the all secured resources
     */
    List<SecuredResourceDTO> getAllSecuredResources();

    /**
     * Gets the all secured resources as flat list
     *
     * @return the all secured resources
     */
    List<SecuredResourceDTO> getSecuredResourcesFlatList();

    /**
     * Gets the all security labels.
     *
     * @return the all security labels
     */
    List<SecurityLabel> getAllSecurityLabels();

    /**
     * Determines is the provided user connected with provided role.
     *
     * @param userName
     *            User name
     * @param roleName
     *            Role name
     * @return <code>true</code> if provided user connected with role, otherwise
     *         <code>false</code>.
     */
    boolean isUserInRole(String userName, String roleName);

    /**
     * Creates the new role.
     *
     * @param role
     *            the role dto
     */
    void create(Role role);

    /**
     * Delete role.
     *
     * @param roleName
     *            the role name
     */
    void delete(String roleName);

    /**
     * Update role.
     *
     * @param roleName
     *            the role name
     * @param role
     *            the role dto
     */
    void update(String roleName, Role role);

    /**
     * Unlink resource.
     *
     * @param roleName
     *            the role name
     * @param resourceName
     *            the resource name
     */
    void unlink(String roleName, String resourceName);

    /**
     * Creates the label.
     *
     * @param label
     *            the label
     */
    void createLabel(SecurityLabel label);

    /**
     * Update label.
     *
     * @param label
     *            the label
     * @param labelName
     *            the label name
     */
    void updateLabel(SecurityLabel label, String labelName);

    /**
     * Find label.
     *
     * @param labelName
     *            the label name
     * @return the security label dto
     */
    SecurityLabel findLabel(String labelName);

    /**
     * Delete label.
     *
     * @param labelName
     *            the label name
     */
    void deleteLabel(String labelName);

    /**
     * Create secured resources.
     * @param resources list with secured resources.
     */
    void createResources(List<SecuredResourceDTO> resources);

    /**
     * Delete resource by name.
     * @param resourceName resource name.
     */
    void deleteResource(String resourceName);

    /**
     * The wrapper for the method above.
     * @param resources the resources to delete
     */
    void deleteResources(List<String> resources);

    /**
     * Update display name for security resource by name
     * @param resourceName security resource name
     * @param resourceDisplayName security resource display name
     * @return true if success, else false
     */
    boolean updateResourceDisplayName(String resourceName, String resourceDisplayName);

    /**
     * Drop all security resources.
     * @param categories the categories to drop.
     */
    void dropResources(String... categories);
    /**
     * Gets the all properties.
     *
     * @return the all properties
     */
    List<RolePropertyDTO> loadAllProperties();

    /**
     * @param property
     */
    void saveProperty(RolePropertyDTO property);

    /**
     * @param id
     */
    void deleteProperty(long id);

    /**
     * @param roleId
     * @return
     */
    List<RolePropertyDTO> loadPropertyValues(int roleId);

    /**
     * @param roleId
     * @param roleProperties
     */
    void savePropertyValues(long roleId, List<CustomProperty> roleProperties);

    List<RoleDTO> loadAllRoles();

    void removeRolesByName(List<String> roles);

    void cleanRolesDataByName(List<String> roles);

    List<Role> loadRolesData(List<String> rolesName);


}
