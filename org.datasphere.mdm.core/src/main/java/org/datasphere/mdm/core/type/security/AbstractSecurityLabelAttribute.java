

package org.datasphere.mdm.core.type.security;

import java.io.Serializable;

/**
 * @author Denis Kostovarov
 */
public abstract class AbstractSecurityLabelAttribute implements SecurityLabelAttribute, Serializable {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -5456945453550299418L;
    private String name;
    private String value;
    private String path;
    private String description;
    private int id;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
