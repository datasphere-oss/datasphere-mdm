

package org.datasphere.mdm.core.type.data;
/**
 * Expecetd attribute data change type.
 * @author Mikhail Mikhailov on Nov 1, 2019
 */
public enum TypeOfChange {
    /**
     * The attribute will be changed.
     */
    CHANGED,
    /**
     * The attribute will be deleted.
     */
    DELETED,
    /**
     * The attribute will be added.
     */
    ADDED,
    /**
     * The attribute remains untouched.
     */
    EXISTING;
}