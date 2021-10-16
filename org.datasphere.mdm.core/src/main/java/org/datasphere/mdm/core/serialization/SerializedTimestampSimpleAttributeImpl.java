

package org.datasphere.mdm.core.serialization;

import java.time.LocalDateTime;

import org.datasphere.mdm.core.type.data.impl.TimestampSimpleAttributeImpl;

/**
 * @author Mikhail Mikhailov
 * Timestamp attribute.
 */
public class SerializedTimestampSimpleAttributeImpl extends TimestampSimpleAttributeImpl implements VerifyableSimpleAttribute<LocalDateTime> {
    /**
     * Constructor.
     */
    public SerializedTimestampSimpleAttributeImpl() {
        super();
    }
}
