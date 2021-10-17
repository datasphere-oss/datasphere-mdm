package org.datasphere.mdm.data.dto;

import org.datasphere.mdm.core.type.calculables.Calculable;
import org.datasphere.mdm.core.type.timeline.Timeline;
import org.datasphere.mdm.draft.type.DraftPayloadResponse;

/**
 * @author Mikhail Mikhailov on Sep 24, 2020
 */
public class GetTimelineResult<X extends Calculable> implements DraftPayloadResponse {
    /**
     * The timeline.
     */
    private Timeline<X> timeline;
    /**
     * Draft ID.
     */
    private long draftId = 0L;
    /**
     * Constructor.
     */
    public GetTimelineResult(Timeline<X> timeline) {
        super();
        this.timeline = timeline;
    }
    /**
     * Constructor.
     */
    public GetTimelineResult(Timeline<X> timeline, long draftId) {
        super();
        this.timeline = timeline;
        this.draftId = draftId;
    }
    /**
     * @return the timeline
     */
    public Timeline<X> getTimeline() {
        return timeline;
    }
    /**
     * @param timeline the timeline to set
     */
    public void setTimeline(Timeline<X> timeline) {
        this.timeline = timeline;
    }
    /**
     * @return the draftId
     */
    public long getDraftId() {
        return draftId;
    }
    /**
     * @param draftId the draftId to set
     */
    public void setDraftId(long draftId) {
        this.draftId = draftId;
    }
}
