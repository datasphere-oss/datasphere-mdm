
package org.datasphere.mdm.core.serialization;

import java.time.LocalDateTime;

import org.datasphere.mdm.core.type.data.impl.TimestampArrayAttributeImpl;

/**
 * @author Mikhail Mikhailov
 * Timestamp array attribute.
 */
public class SerializedTimestampArrayAttributeImpl extends TimestampArrayAttributeImpl implements VerifyableArrayAttribute<LocalDateTime> {
    /**
     * Constructor.
     */
    public SerializedTimestampArrayAttributeImpl() {
        super();
    }
}
