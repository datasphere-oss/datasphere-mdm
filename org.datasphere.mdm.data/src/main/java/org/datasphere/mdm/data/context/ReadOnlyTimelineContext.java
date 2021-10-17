

package org.datasphere.mdm.data.context;

import org.datasphere.mdm.core.type.calculables.Calculable;
import org.datasphere.mdm.core.type.timeline.Timeline;
import org.datasphere.mdm.system.context.StorageCapableContext;
import org.datasphere.mdm.system.context.StorageId;
import org.datasphere.mdm.system.type.pipeline.PipelineInput;

/**
 * @author Mikhail Mikhailov
 * Read only data view - variants of GET (data, rel, clsf, etc).
 */
public interface ReadOnlyTimelineContext<T extends Calculable> extends StorageCapableContext, PipelineInput {
    /**
     * Current timeline.
     */
    StorageId SID_CURRENT_TIMELINE = new StorageId("CURRENT_TIMELINE");
    /**
     * Get TL.
     * @return timeline
     */
    default Timeline<T> currentTimeline() {
        return getFromStorage(SID_CURRENT_TIMELINE);
    }

    /**
     * Put TL.
     * @param timeline
     */
    default void currentTimeline(Timeline<T> timeline) {
        putToStorage(SID_CURRENT_TIMELINE, timeline);
    }

}
