

package org.datasphere.mdm.core.type.change;

/**
 * @author Mikhail Mikhailov
 * Change set interface.
 */
public interface ChangeSet {
    /**
     * Tells whether change set is empty.
     * @return true, if empty, false otherwise
     */
    boolean isEmpty();
    /**
     * Clear set state.
     */
    void clear();
}
