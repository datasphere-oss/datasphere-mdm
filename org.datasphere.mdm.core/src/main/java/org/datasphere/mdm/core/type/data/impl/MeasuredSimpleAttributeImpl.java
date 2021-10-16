

package org.datasphere.mdm.core.type.data.impl;

import javax.annotation.Nonnull;

import org.datasphere.mdm.core.type.data.MeasuredValue;
import org.datasphere.mdm.core.type.data.SimpleAttribute;

public class MeasuredSimpleAttributeImpl extends AbstractSimpleAttribute<MeasuredValue> {
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected MeasuredSimpleAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name the attr name
     */
    public MeasuredSimpleAttributeImpl(String name) {
        this(name, MeasuredValue.of(null));
    }
    /**
     * Constructor.
     * @param name the attr name
     * @param value the value hold
     */
    public MeasuredSimpleAttributeImpl(String name, MeasuredValue value) {
        super(name, value);
    }
    /**
     * Constructor. Some value in initial units for the category, defined in MM.
     * @param name the attr name
     * @param value the value hold
     */
    public MeasuredSimpleAttributeImpl(String name, Double value) {
        this(name, MeasuredValue.of(value));
    }
    /**
     * Constructor.
     * @param numberSimpleAttribute a simple attribute to convert
     */
    public MeasuredSimpleAttributeImpl(@Nonnull SimpleAttribute<Double> numberSimpleAttribute) {
        this(numberSimpleAttribute.getName(), numberSimpleAttribute.getValue());
    }
    /**
     * Constructor.
     * @param name the attr name
     * @param categoryId the category ID
     * @param unitId the unit ID
     * @param initial the initial value
     */
    public MeasuredSimpleAttributeImpl(@Nonnull String name, String categoryId, String unitId, Double initial) {
        this(name, MeasuredValue.of(categoryId, unitId, initial));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public SimpleDataType getDataType() {
        return SimpleDataType.MEASURED;
    }
}
