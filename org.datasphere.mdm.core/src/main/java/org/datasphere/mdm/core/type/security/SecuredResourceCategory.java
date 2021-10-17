
/**
 *
 */
package org.datasphere.mdm.core.type.security;


/**
 * @author Mikhail Mikhailov
 * Secured resource category.
 */
public enum SecuredResourceCategory {
    /**
     * System.
     */
    SYSTEM,
    /**
     * Resource is of the meta model category (i. e. Entity, Lookup entity, etc.).
     */
    META_MODEL,
    /**
     * Resource is of the classifier category.
     */
    CLASSIFIER;
}
