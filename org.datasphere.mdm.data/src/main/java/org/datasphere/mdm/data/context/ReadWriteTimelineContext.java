

package org.datasphere.mdm.data.context;

import org.datasphere.mdm.core.type.calculables.Calculable;
import org.datasphere.mdm.core.type.timeline.Timeline;
import org.datasphere.mdm.system.context.StorageId;

/**
 * @author Mikhail Mikhailov
 * Modifying stuff.
 */
public interface ReadWriteTimelineContext<T extends Calculable> extends ReadOnlyTimelineContext<T> {
    /**
     * Current timeline.
     */
    StorageId SID_NEXT_TIMELINE = new StorageId("NEXT_TIMELINE");
    /**
     * Get TL.
     * @return timeline
     */
    default Timeline<T> nextTimeline() {
        return getFromStorage(SID_NEXT_TIMELINE);
    }
    /**
     * Put TL.
     * @param timeline
     */
    default void nextTimeline(Timeline<T> timeline) {
        putToStorage(SID_NEXT_TIMELINE, timeline);
    }
}
