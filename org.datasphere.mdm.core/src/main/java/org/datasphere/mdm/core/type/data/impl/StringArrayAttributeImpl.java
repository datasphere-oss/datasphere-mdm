

package org.datasphere.mdm.core.type.data.impl;

import java.util.List;

import org.datasphere.mdm.core.type.data.ArrayValue;

/**
 * @author Mikhail Mikhailov
 * Array of strings.
 */
public class StringArrayAttributeImpl extends AbstractArrayAttribute<String> {
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected StringArrayAttributeImpl() {
        super();
    }
    /**
     * Constructor.
     * @param name
     */
    public StringArrayAttributeImpl(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param name
     * @param value
     */
    public StringArrayAttributeImpl(String name, List<ArrayValue<String>> value) {
        super(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayDataType getDataType() {
        return ArrayDataType.STRING;
    }
}
