

package org.datasphere.mdm.core.type.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Kostovarov
 */
public abstract class AbstractSecurityLabel implements SecurityLabel, Serializable {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -4760502359316421111L;
    private String name;
    private String displayName;
    private String description;
    private List<SecurityLabelAttribute> attributes;

    public AbstractSecurityLabel() {
        attributes = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public List<SecurityLabelAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setAttributes(final List<SecurityLabelAttribute> attributes) {
        this.attributes = attributes;
    }
}
