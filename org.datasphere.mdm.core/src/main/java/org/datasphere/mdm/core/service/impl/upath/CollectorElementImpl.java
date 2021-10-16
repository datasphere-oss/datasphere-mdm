
package org.datasphere.mdm.core.service.impl.upath;

import org.datasphere.mdm.core.type.upath.UPathCollectorElement;
import org.datasphere.mdm.core.type.upath.UPathElementType;

/**
 * @author Mikhail Mikhailov on Feb 26, 2021
 * Collector. Nothing special for collectors so far.
 */
public class CollectorElementImpl extends AbstractElementImpl implements UPathCollectorElement {
    /**
     * Constructor.
     * @param element the source element
     */
    public CollectorElementImpl(String element) {
        super(element, UPathElementType.COLLECTOR);
    }
}
