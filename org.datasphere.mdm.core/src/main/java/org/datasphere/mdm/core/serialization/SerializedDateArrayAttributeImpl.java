

package org.datasphere.mdm.core.serialization;

import java.time.LocalDate;

import org.datasphere.mdm.core.type.data.impl.DateArrayAttributeImpl;

/**
 * @author Mikhail Mikhailov
 * Date array attribute.
 */
public class SerializedDateArrayAttributeImpl extends DateArrayAttributeImpl implements VerifyableArrayAttribute<LocalDate> {
    /**
     * Constructor.
     */
    public SerializedDateArrayAttributeImpl() {
        super();
    }
}
