
package org.datasphere.mdm.core.service.job.step;

import org.springframework.batch.core.ItemProcessListener;

/**
 * @author Mikhail Mikhailov on Jul 13, 2021
 * Item process listener adapter.
 */
public class ItemProcessListenerAdapter<T, S> implements ItemProcessListener<T, S> {
    /**
     * Constructor.
     */
    public ItemProcessListenerAdapter() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeProcess(T item) {
        // Override
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterProcess(T item, S result) {
        // Override
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onProcessError(T item, Exception e) {
        // Override
    }
}
