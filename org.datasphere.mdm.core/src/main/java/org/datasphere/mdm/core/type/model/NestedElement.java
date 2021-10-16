
package org.datasphere.mdm.core.type.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * @author Mikhail Mikhailov on Oct 17, 2020
 * Nested element.
 */
public interface NestedElement extends EntityElement {
    /**
     * Gets registers, referencing this nested element.
     *
     * @return collection
     */
    @Nonnull
    Collection<RegisterElement> getReferencingRegisters();
    /**
     * Gets nested elements, referencing this nested element.
     *
     * @return collection of nested elements
     */
    @Nonnull
    Collection<NestedElement> getReferencingNesteds();
    /**
     * Gets lookups, referenced from this register alone with atributes.
     *
     * @return lookups map.
     */
    @Nonnull
    Map<LookupElement, Set<AttributeElement>> getReferencedLookups();
    /**
     * Gets all nested elements referenced by this nested element.
     *
     * @return collection of nested elements
     */
    @Nonnull
    Collection<NestedElement> getReferencedNesteds();
}
