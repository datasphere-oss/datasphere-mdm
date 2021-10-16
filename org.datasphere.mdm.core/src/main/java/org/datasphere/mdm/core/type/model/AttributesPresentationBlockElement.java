

package org.datasphere.mdm.core.type.model;
/**
 * @author Mikhail Mikhailov on Oct 21, 2020
 * Attributes presentation.
 */

import java.util.Collection;

public interface AttributesPresentationBlockElement extends PresentationBlockElement {
    /**
     * Gets the attributes of the block.
     * @return attributes
     */
    Collection<String> getAttributes();
}
