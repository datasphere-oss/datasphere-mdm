package org.datasphere.mdm.data.context;

import java.util.Date;

import org.apache.commons.lang3.tuple.Pair;

/**
 * @author Mikhail Mikhailov on Nov 18, 2020
 * Basic timeline query fields.
 */
public interface TimelineQueryContext {
    /**
     * Reduce resulting timeline to a single period, where given date falls in.
     * @return the forDate
     */
    Date getForDate();
    /**
     * Reduce resulting timeline to a several periods, bounded to the given frame.
     * @return the dates frame
     */
    Pair<Date, Date> getForDatesFrame();
    /**
     * Gets timelines view only if it has updates after the given date.
     * @return the updatesAfter
     */
    Date getForUpdatesAfter();
    /**
     * Gets timeline view as it was at the time of this last update.
     * @return the lastUpdate
     */
    Date getForLastUpdate();
    /**
     * Gets timeline view as it was at the time of update with this operation id.
     * @return the forOperationId
     */
    String getForOperationId();
    /**
     * Fetch and return keys as object for this timeline or not.
     * @return the flag
     */
    boolean isFetchKeys();
    /**
     * Fetch and return data for this timeline or not (only periods in the latter case).
     * @return the fetchTimelineData
     */
    boolean isFetchData();
    /**
     * Skip or perform etalon calculations.
     * @return the skipCalculations
     */
    boolean isSkipCalculations();
}
