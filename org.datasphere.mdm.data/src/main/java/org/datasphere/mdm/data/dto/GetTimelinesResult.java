
package org.datasphere.mdm.data.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.datasphere.mdm.core.type.calculables.Calculable;

/**
 * @author Mikhail Mikhailov on Apr 17, 2021
 */
public class GetTimelinesResult<X extends Calculable> {

    private Map<String, List<GetTimelineResult<X>>> timelines;

    /**
     * Constructor.
     */
    public GetTimelinesResult() {
        super();
    }
    /**
     * Constructor.
     */
    public GetTimelinesResult(Map<String, List<GetTimelineResult<X>>> timelines) {
        this();
        this.timelines = timelines;
    }
    /**
     * @return the timelines
     */
    public Map<String, List<GetTimelineResult<X>>> getTimelines() {
        return Objects.isNull(timelines) ? Collections.emptyMap() : timelines;
    }
    /**
     * @return the timelines
     */
    public List<GetTimelineResult<X>> getTimelines(String key) {
        List<GetTimelineResult<X>> selection = getTimelines().get(key);
        return Objects.isNull(selection) ? Collections.emptyList() : selection;
    }
    /**
     * @param timelines the timelines to set
     */
    public void setTimelines(Map<String, List<GetTimelineResult<X>>> timelines) {
        this.timelines = timelines;
    }
    /**
     * Adds a timeline to keyed collection.
     * @param key the collection key
     * @param timeline the timeline to add
     */
    public void add(String key, GetTimelineResult<X> timeline) {

        if (Objects.nonNull(timeline)) {

            if (Objects.isNull(timelines)) {
                timelines = new HashMap<>();
            }

            timelines.computeIfAbsent(key, k -> new ArrayList<>())
                     .add(timeline);
        }
    }
}
