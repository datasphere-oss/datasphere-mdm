

package org.datasphere.mdm.core.type.calculables.impl;

import org.datasphere.mdm.core.type.calculables.CalculableHolder;

/**
 * @author Mikhail Mikhailov
 * Abstract parent for holders, for box key primarily.
 */
public abstract class AbstractCalculableHolder<T> implements CalculableHolder<T> {
    /**
     * Saved box key.
     */
    protected String boxKey;
    /**
     * Constructor.
     */
    protected AbstractCalculableHolder() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toBoxKey() {

        if (boxKey == null) {
            boxKey = getOriginKey().toBoxKey();
        }

        return boxKey;
    }
}
