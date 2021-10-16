

package org.datasphere.mdm.core.dao.tokenizer;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Mikhailov
 * Simple forward iterator for {@link PGtokenizer}.
 */
public class CompositeValueIterator implements Iterator<String> {
    /**
     * The tokenizer.
     */
    private CompositeValueTokenizer cvt;
    /**
     * Current position.
     */
    private int cursor = 0;
    /**
     * Constructor.
     */
    public CompositeValueIterator(CompositeValueTokenizer cvt) {
        super();
        this.cvt = cvt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return cvt.getSize() > cursor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String next() {

        if (!hasNext()) {
            throw new NoSuchElementException("no (more) tokens in CompositeValueTokenizer");
        }

        return cvt.getToken(cursor++);
    }
}
