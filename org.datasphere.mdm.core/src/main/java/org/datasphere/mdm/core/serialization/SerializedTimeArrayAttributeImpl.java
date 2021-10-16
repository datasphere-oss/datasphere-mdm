

package org.datasphere.mdm.core.serialization;

import java.time.LocalTime;

import org.datasphere.mdm.core.type.data.impl.TimeArrayAttributeImpl;

/**
 * @author Mikhail Mikhailov
 * Time array attribute.
 */
public class SerializedTimeArrayAttributeImpl extends TimeArrayAttributeImpl implements VerifyableArrayAttribute<LocalTime> {
    /**
     * Constructor.
     */
    public SerializedTimeArrayAttributeImpl() {
        super();
    }
}
