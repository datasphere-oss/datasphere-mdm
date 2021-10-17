

package org.datasphere.mdm.core.type.security;

import java.io.Serializable;

/**
 * @author Denis Kostovarov
 */
public abstract class AbstractSecuredResource implements SecuredResource, Serializable {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -4784151607289806225L;
    /**
     * Name.
     */
    private String name;
    /**
     * Display name.
     */
    private String displayName;
    /**
     * Type.
     */
    private SecuredResourceType type;
    /**
     * Category.
     */
    private String category;
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public SecuredResourceType getType() {
        return type;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategory() {
        return category;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getDisplayName() {
        return displayName;
    }
    /**
     * Sets name.
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * Sets display name.
     * @param displayName the display name
     */
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    /**
     * Sets type.
     * @param securedResourceType the type to set
     */
    public void setType(final SecuredResourceType securedResourceType) {
        this.type = securedResourceType;
    }
    /**
     * Sets a category.
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
