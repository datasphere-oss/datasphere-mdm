

package org.datasphere.mdm.core.type.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * Code attribute holder element.
 * @author Mikhail Mikhailov on Nov 7, 2019
 */
public interface LookupElement extends EntityElement {
    /**
     * Gets the code attribute.
     *
     * @return the code attribute
     */
    @Nonnull
    AttributeElement getCodeAttribute();
    /**
     * Gets code alternative attributes.
     *
     * @return attributes collection
     */
    @Nonnull
    Collection<AttributeElement> getCodeAliases();
    /**
     * Gets lookups, referenced from this lookup alone with atributes.
     *
     * @return lookups map.
     */
    @Nonnull
    Map<LookupElement, Set<AttributeElement>> getReferencedLookups();
    /**
     * Gets lookups, referencing this lookup alone with atributes.
     *
     * @return lookups map.
     */
    @Nonnull
    Map<LookupElement, Set<AttributeElement>> getReferencingLookups();
    /**
     * Gets registers, referencing this lookup.
     *
     * @return registers with attributes
     */
    @Nonnull
    Map<RegisterElement, Set<AttributeElement>> getReferencingRegisters();
    /**
     * Gets nested elements, referencing this lookup.
     *
     * @return nested elements with attributes
     */
    @Nonnull
    Map<NestedElement, Set<AttributeElement>> getReferencingNesteds();
    /**
     * Gets relation elements, referencing this lookup.
     *
     * @return relation elements with their attributes
     */
    @Nonnull
    Map<RelationElement, Set<AttributeElement>> getReferencingRelations();
}
