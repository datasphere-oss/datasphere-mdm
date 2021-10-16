

package org.datasphere.mdm.core.type.data.impl;

import java.time.LocalTime;

/**
 * @author Mikhail Mikhailov
 * Time value.
 */
public class TimeSimpleAttributeImpl extends AbstractSimpleAttribute<LocalTime> {

    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected TimeSimpleAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name
     */
    public TimeSimpleAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name the name
     * @param value the value
     */
    public TimeSimpleAttributeImpl(String name, LocalTime value) {
        super(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleDataType getDataType() {
        return SimpleDataType.TIME;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public TimeSimpleAttributeImpl withValue(LocalTime value) {
        setValue(value);
        return this;
    }
}
