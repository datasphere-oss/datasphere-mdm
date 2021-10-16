
package org.datasphere.mdm.core.type.model.source;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * General purpose custom property.
 */
public class CustomProperty implements Serializable {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = -2004657676497629391L;

    @JacksonXmlProperty(isAttribute = true)
    protected String name;

    @JacksonXmlProperty(isAttribute = true)
    protected String value;

    @Override
    public String toString() {
        return "CustomProperty{" + "name='" + name + "', value='" + value + "'}";
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CustomProperty withName(String value) {
        setName(value);
        return this;
    }

    public CustomProperty withValue(String value) {
        setValue(value);
        return this;
    }
}
