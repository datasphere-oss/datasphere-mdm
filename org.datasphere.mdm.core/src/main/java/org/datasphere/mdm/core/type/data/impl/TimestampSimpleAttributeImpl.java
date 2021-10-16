

package org.datasphere.mdm.core.type.data.impl;

import java.time.LocalDateTime;

/**
 * @author Mikhail Mikhailov
 * Timestamp simple attribute.
 */
public class TimestampSimpleAttributeImpl extends AbstractSimpleAttribute<LocalDateTime> {

    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected TimestampSimpleAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name
     */
    public TimestampSimpleAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name the name
     * @param value the value
     */
    public TimestampSimpleAttributeImpl(String name, LocalDateTime value) {
        super(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleDataType getDataType() {
        return SimpleDataType.TIMESTAMP;
    }

    /**
     * Fluent part for compatibility.
     * @param value the value
     * @return self
     */
    public TimestampSimpleAttributeImpl withValue(LocalDateTime value) {
        setValue(value);
        return this;
    }
}
