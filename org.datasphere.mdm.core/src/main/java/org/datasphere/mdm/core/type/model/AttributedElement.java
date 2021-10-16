

package org.datasphere.mdm.core.type.model;

import java.util.Map;

/**
 * @author Mikhail Mikhailov
 * Attributes map wrapper.
 */
public interface AttributedElement {
    /**
     * Gets attributes map.
     * @return map
     */
    Map<String, AttributeElement> getAttributes();
    /**
     * Filters attributes map to return only main displayable attributes.
     * @return map
     */
    Map<String, AttributeElement> getMainDisplayableAttributes();
}
