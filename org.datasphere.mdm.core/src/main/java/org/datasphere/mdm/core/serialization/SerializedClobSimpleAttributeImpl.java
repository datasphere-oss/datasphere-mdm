

package org.datasphere.mdm.core.serialization;

import org.datasphere.mdm.core.type.data.CharacterLargeValue;
import org.datasphere.mdm.core.type.data.impl.ClobSimpleAttributeImpl;

/**
 * @author Mikhail Mikhailov
 * Clob attribute.
 */
public class SerializedClobSimpleAttributeImpl extends ClobSimpleAttributeImpl implements VerifyableSimpleAttribute<CharacterLargeValue> {
    /**
     * Constructor.
     */
    public SerializedClobSimpleAttributeImpl() {
        super();
    }
}
