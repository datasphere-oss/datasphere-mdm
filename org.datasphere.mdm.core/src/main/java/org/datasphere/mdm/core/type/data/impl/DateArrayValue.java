

package org.datasphere.mdm.core.type.data.impl;

import java.time.LocalDate;

/**
 * @author Mikhail Mikhailov
 * Local date value
 */
public class DateArrayValue extends AbstractArrayValue<LocalDate> {

    /**
     * Constructor.
     */
    public DateArrayValue() {
        super();
    }

    /**
     * Constructor.
     * @param value the value
     * @param displayValue the display value
     */
    public DateArrayValue(LocalDate value, String displayValue) {
        super(value, displayValue);
    }

    /**
     * Constructor.
     * @param value the value
     */
    public DateArrayValue(LocalDate value) {
        super(value);
    }

}
