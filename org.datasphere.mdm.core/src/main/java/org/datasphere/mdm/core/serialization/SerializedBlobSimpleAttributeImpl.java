
package org.datasphere.mdm.core.serialization;

import org.datasphere.mdm.core.type.data.BinaryLargeValue;
import org.datasphere.mdm.core.type.data.impl.BlobSimpleAttributeImpl;

/**
 * @author Mikhail Mikhailov
 * Blob attribute.
 */
public class SerializedBlobSimpleAttributeImpl extends BlobSimpleAttributeImpl implements VerifyableSimpleAttribute<BinaryLargeValue> {
    /**
     * Constructor.
     */
    public SerializedBlobSimpleAttributeImpl() {
        super();
    }
}
