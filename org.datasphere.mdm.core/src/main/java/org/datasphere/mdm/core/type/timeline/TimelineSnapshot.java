

package org.datasphere.mdm.core.type.timeline;

import org.datasphere.mdm.core.type.calculables.Calculable;

/**
 * @author Mikhail Mikhailov
 * Simple next and previous timeline change view.
 */
public final class TimelineSnapshot <T extends Calculable> {
    /**
     * Previous TL state.
     */
    private final Timeline<T> previous;
    /**
     * Next TL state.
     */
    private final Timeline<T> next;
    /**
     * Constructor.
     * @param previous the former state
     * @param next the next state
     */
    public TimelineSnapshot(Timeline<T> previous, Timeline<T> next) {
        super();
        this.previous = previous;
        this.next = next;
    }
    /**
     * @return the previous
     */
    public Timeline<T> getPrevious() {
        return previous;
    }
    /**
     * @return the next
     */
    public Timeline<T> getNext() {
        return next;
    }

}
