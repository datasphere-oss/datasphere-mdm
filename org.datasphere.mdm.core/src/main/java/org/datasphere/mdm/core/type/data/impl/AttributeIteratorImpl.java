
package org.datasphere.mdm.core.type.data.impl;

import java.util.Iterator;
import java.util.Map.Entry;

import org.datasphere.mdm.core.type.data.Attribute;
import org.datasphere.mdm.core.type.data.AttributeIterator;

/**
 * @author Mikhail Mikhailov
 * Thin iterator wrapper to allow traverse -&gt; remove actions.
 */
public class AttributeIteratorImpl implements AttributeIterator {

    /**
     * The underlaying iterator.
     */
    private final Iterator<Entry<String, Attribute>> it;
    /**
     * Constructor.
     * @param it iterator.
     */
    AttributeIteratorImpl(Iterator<Entry<String, Attribute>> it) {
        this.it = it;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Attribute next() {
        return it.next().getValue();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        it.remove();
    }
}
