

package org.datasphere.mdm.core.type.model;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 * @author Mikhail Mikhailov on Oct 16, 2020
 * Relation access interface.
 */
public interface RelationElement extends EntityElement {
    /**
     * True, if relation is required.
     * @return true, if required.
     */
    boolean isRequired();
    /**
     * Relation is of type REFERENCE.
     * @return true for REFERENCE
     */
    boolean isReference();
    /**
     * Relation is of type CONTAINS.
     * @return true for CONTAINS
     */
    boolean isContainment();
    /**
     * Relation is of type M2M.
     * @return true for M2M
     */
    boolean isManyToMany();
    /**
     * Gets left (from) entity.
     * @return the left (from) entity
     */
    @Nonnull
    RegisterElement getLeft();
    /**
     * Gets the left entity presentation rules.
     * @return left presentation rules
     */
    @Nonnull
    ReferencePresentationElement getLeftPresentation();
    /**
     * Gets right (to) entity.
     * @return the right (to) entity
     */
    @Nonnull
    RegisterElement getRight();
    /**
     * Gets the right entity presentation rules.
     * @return right presentation rules
     */
    @Nonnull
    ReferencePresentationElement getRightPresentation();
    /**
     * Gets lookups, referenced from this register alone with atributes.
     *
     * @return lookups map.
     */
    @Nonnull
    Map<LookupElement, Set<AttributeElement>> getReferencedLookups();
}
