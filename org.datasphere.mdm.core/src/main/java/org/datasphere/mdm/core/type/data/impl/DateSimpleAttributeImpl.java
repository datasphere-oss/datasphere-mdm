

package org.datasphere.mdm.core.type.data.impl;

import java.time.LocalDate;

/**
 * @author Mikhail Mikhailov
 * Date simple attribute.
 */
public class DateSimpleAttributeImpl extends AbstractSimpleAttribute<LocalDate> {

    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected DateSimpleAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name the name
     */
    public DateSimpleAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name the name
     * @param value the value
     */
    public DateSimpleAttributeImpl(String name, LocalDate value) {
        super(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleDataType getDataType() {
        return SimpleDataType.DATE;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public DateSimpleAttributeImpl withValue(LocalDate value) {
        setValue(value);
        return this;
    }
}
