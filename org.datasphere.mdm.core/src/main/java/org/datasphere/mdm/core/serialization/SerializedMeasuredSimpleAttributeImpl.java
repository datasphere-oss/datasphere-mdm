
package org.datasphere.mdm.core.serialization;

import org.datasphere.mdm.core.type.data.MeasuredValue;
import org.datasphere.mdm.core.type.data.impl.MeasuredSimpleAttributeImpl;

/**
 * @author Mikhail Mikhailov
 * Measured attribute.
 */
public class SerializedMeasuredSimpleAttributeImpl extends MeasuredSimpleAttributeImpl implements VerifyableSimpleAttribute<MeasuredValue> {
    /**
     * Constructor.
     */
    public SerializedMeasuredSimpleAttributeImpl() {
        super();
    }
}
