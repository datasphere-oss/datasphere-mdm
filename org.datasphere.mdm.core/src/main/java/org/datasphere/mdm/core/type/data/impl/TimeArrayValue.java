

package org.datasphere.mdm.core.type.data.impl;

import java.time.LocalTime;

/**
 * @author Mikhail Mikhailov
 * Local time array value.
 */
public class TimeArrayValue extends AbstractArrayValue<LocalTime> {
    /**
     * Constructor.
     */
    public TimeArrayValue() {
        super();
    }

    /**
     * Constructor.
     * @param value
     * @param displayValue
     */
    public TimeArrayValue(LocalTime value, String displayValue) {
        super(value, displayValue);
    }

    /**
     * Constructor.
     * @param value
     */
    public TimeArrayValue(LocalTime value) {
        super(value);
    }
}
