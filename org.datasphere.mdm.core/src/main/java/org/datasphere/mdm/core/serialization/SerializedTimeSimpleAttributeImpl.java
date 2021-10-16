

package org.datasphere.mdm.core.serialization;

import java.time.LocalTime;

import org.datasphere.mdm.core.type.data.impl.TimeSimpleAttributeImpl;

/**
 * @author Mikhail Mikhailov
 * Time attribute.
 */
public class SerializedTimeSimpleAttributeImpl extends TimeSimpleAttributeImpl implements VerifyableSimpleAttribute<LocalTime> {
    /**
     * Constructor.
     */
    public SerializedTimeSimpleAttributeImpl() {
        super();
    }
}
