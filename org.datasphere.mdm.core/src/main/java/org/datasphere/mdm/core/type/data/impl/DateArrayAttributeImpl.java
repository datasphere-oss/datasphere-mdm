

package org.datasphere.mdm.core.type.data.impl;

import java.time.LocalDate;
import java.util.List;

import org.datasphere.mdm.core.type.data.ArrayValue;

/**
 * @author Mikhail Mikhailov
 * Array of local date values.
 */
public class DateArrayAttributeImpl extends AbstractArrayAttribute<LocalDate> {
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected DateArrayAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name
     */
    public DateArrayAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name
     * @param value
     */
    public DateArrayAttributeImpl(String name, List<ArrayValue<LocalDate>> value) {
        super(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayDataType getDataType() {
        return ArrayDataType.DATE;
    }
}
