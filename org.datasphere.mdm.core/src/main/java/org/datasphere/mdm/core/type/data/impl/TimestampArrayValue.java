

package org.datasphere.mdm.core.type.data.impl;

import java.time.LocalDateTime;

/**
 * @author Mikhail Mikhailov
 * Local date time array value.
 */
public class TimestampArrayValue extends AbstractArrayValue<LocalDateTime> {
    /**
     * Constructor.
     */
    public TimestampArrayValue() {
        super();
    }

    /**
     * Constructor.
     * @param value
     * @param displayValue
     */
    public TimestampArrayValue(LocalDateTime value, String displayValue) {
        super(value, displayValue);
    }

    /**
     * Constructor.
     * @param value
     */
    public TimestampArrayValue(LocalDateTime value) {
        super(value);
    }}
