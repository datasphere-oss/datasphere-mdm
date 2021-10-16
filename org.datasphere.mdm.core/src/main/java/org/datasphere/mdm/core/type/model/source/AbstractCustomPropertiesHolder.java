
package org.datasphere.mdm.core.type.model.source;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * @author Mikhail Mikhailov on Oct 6, 2020
 */
public abstract class AbstractCustomPropertiesHolder<X extends AbstractCustomPropertiesHolder<X>> implements Serializable {
    /**
     * GSVUID.
     */
    private static final long serialVersionUID = 2465578389809563969L;
    /**
     * The properties.
     */
    @JacksonXmlElementWrapper(localName = "properties", useWrapping = true)
    @JacksonXmlProperty(localName = "property")
    private List<CustomProperty> customProperties;
    /**
     * Constructor.
     */
    protected AbstractCustomPropertiesHolder() {
        super();
    }

    public List<CustomProperty> getCustomProperties() {
        if (customProperties == null) {
            customProperties = new ArrayList<>();
        }
        return customProperties;
    }

    public void setCustomProperties(List<CustomProperty> customProperties) {
        this.customProperties = customProperties;
    }

    public X withCustomProperties(CustomProperty... values) {
        if (ArrayUtils.isNotEmpty(values)) {
            return withCustomProperties(Arrays.asList(values));
        }
        return self();
    }

    public X withCustomProperties(Collection<CustomProperty> values) {
        if (CollectionUtils.isNotEmpty(values)) {
            getCustomProperties().addAll(values);
        }
        return self();
    }

    @SuppressWarnings("unchecked")
    protected X self() {
        return (X) this;
    }
}
