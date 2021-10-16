

package org.datasphere.mdm.core.serialization;

import java.time.LocalDate;

import org.datasphere.mdm.core.type.data.impl.DateSimpleAttributeImpl;

/**
 * @author Mikhail Mikhailov
 * Date attribute.
 */
public class SerializedDateSimpleAttributeImpl extends DateSimpleAttributeImpl implements VerifyableSimpleAttribute<LocalDate> {
    /**
     * Constructor.
     */
    public SerializedDateSimpleAttributeImpl() {
        super();
    }
}
