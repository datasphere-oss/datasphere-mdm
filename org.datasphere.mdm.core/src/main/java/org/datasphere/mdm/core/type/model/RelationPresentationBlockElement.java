

package org.datasphere.mdm.core.type.model;

import java.util.Collection;

/**
 * @author Mikhail Mikhailov on Oct 21, 2020
 * Relations presentation info.
 */
public interface RelationPresentationBlockElement extends PresentationBlockElement {

    enum RelationTypeInfo {
        /**
         * "References".
         */
        REFERENCES,
        /**
         * "Contains"
         */
        CONTAINS,
        /**
         * "ManyToMany"
         */
        MANY_TO_MANY
    }

    /**
     * Gets relation names.
     * @return relation names
     */
    Collection<String> getRelations();
    /**
     * Gets type info.
     * @return type info
     */
    RelationTypeInfo getTypeInfo();
}
