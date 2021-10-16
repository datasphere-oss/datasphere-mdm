

package org.datasphere.mdm.core.type.model;

import java.util.Collection;

/**
 * Marks searchable model elements.
 *
 * @author Mikhail Mikhailov
 */
public interface SearchableElement extends AttributedElement {
    /**
     * Gets a collection of model element infos, that should be put to index.
     * @return a collection of model element infos, that should be put to index
     */
    Collection<ModelSearchEntry> getModelSearchEntries();
}
