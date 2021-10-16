
package org.datasphere.mdm.core.type.model;

/**
 * @author Yaroslav Nikolaev
 * Top level model type wrapper.
 */
public interface IdentityElement extends ModelElement {
    /**
     * Gets the unique identifier in the same objectclass.
     *
     * @return identifier
     */
    String getId();
}
