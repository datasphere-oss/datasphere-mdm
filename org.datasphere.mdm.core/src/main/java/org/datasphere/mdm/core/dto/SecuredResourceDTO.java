

package org.datasphere.mdm.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.datasphere.mdm.core.type.security.SecuredResource;
import org.datasphere.mdm.core.type.security.SecuredResourceType;

/**
 * The Class SecuredResourceRO.
 * @author ilya.bykov
 */
public class SecuredResourceDTO extends BaseSecurityDTO implements SecuredResource, Serializable {

    /**
     * SVUID.
     */
    private static final long serialVersionUID = 3212929937675356287L;

    /** The name. */
    private String name;

    /** The display name. */
    private String displayName;

    /** The type. */
    private SecuredResourceType type;

    /** The type. */
    private String category;

    /**
     * Parent resource.
     */
    private SecuredResourceDTO parent;

    /** Children. */
    private Collection<SecuredResourceDTO> children = new ArrayList<>();
    /**
     * Default constructor.
     */
    public SecuredResourceDTO() {
        super();
    }
    /**
     * Default constructor.
     * @param other object to copy fields from
     */
    public SecuredResourceDTO(SecuredResource other) {
        super();
        if (Objects.nonNull(other)) {
            this.name = other.getName();
            this.displayName = other.getDisplayName();
            this.type = other.getType();
            this.category = other.getCategory();
        }
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    @Override
    public SecuredResourceType getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type to set
     */
    public void setType(SecuredResourceType type) {
        this.type = type;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    /**
	 * Gets the display name.
	 *
	 * @return the displayName
	 */
    @Override
    public String getDisplayName() {
        return displayName;
    }

    /**
	 * Sets the display name.
	 *
	 * @param displayName
	 *            the displayName to set
	 */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the parent
     */
    public SecuredResourceDTO getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(SecuredResourceDTO parent) {
        this.parent = parent;
    }
    /**
     * @return the children
     */
    public Collection<SecuredResourceDTO> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(Collection<SecuredResourceDTO> children) {
        this.children = children;
    }
}
