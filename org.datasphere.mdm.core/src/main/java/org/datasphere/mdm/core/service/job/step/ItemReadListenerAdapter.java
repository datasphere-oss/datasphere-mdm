
package org.datasphere.mdm.core.service.job.step;

import org.springframework.batch.core.ItemReadListener;

/**
 * @author Mikhail Mikhailov on Jul 13, 2021
 * Read item adapter.
 */
public class ItemReadListenerAdapter<T> implements ItemReadListener<T> {
    /**
     * Constructor.
     */
    public ItemReadListenerAdapter() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeRead() {
        // Override
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterRead(T item) {
        // Override
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onReadError(Exception ex) {
        // Override
    }
}
