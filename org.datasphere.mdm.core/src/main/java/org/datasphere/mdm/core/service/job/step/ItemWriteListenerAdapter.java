
package org.datasphere.mdm.core.service.job.step;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;

/**
 * @author Mikhail Mikhailov on Jul 13, 2021
 * Item write listener adapter.
 */
public class ItemWriteListenerAdapter<S> implements ItemWriteListener<S> {
    /**
     * Constructor.
     */
    public ItemWriteListenerAdapter() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void beforeWrite(List<? extends S> items) {
        // Override
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterWrite(List<? extends S> items) {
        // Override
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onWriteError(Exception exception, List<? extends S> items) {
        // Override
    }
}
