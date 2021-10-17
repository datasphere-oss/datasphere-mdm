
/**
 *
 */
package org.datasphere.mdm.data.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.datasphere.mdm.core.type.keys.Keys;

/**
 * @author Mikhail Mikhailov
 *
 */
public class TimelineDTO {

    /**
     * Etalon id.
     */
    private final String etalonId;

    private Keys<?, ?> keys;

    /**
     * Intervals.
     */
    private final List<TimeIntervalDTO> intervals = new ArrayList<>();

    /**
     * Constructor.
     */
    public TimelineDTO(String etalonId) {
        super();
        this.etalonId = etalonId;
    }

    public TimelineDTO(String etalonId, Keys keys) {
        super();
        this.etalonId = etalonId;
        this.keys = keys;
    }

    /**
     * @return the etalonId
     */
    public String getEtalonId() {
        return etalonId;
    }


    /**
     * @return the intervals
     */
    public List<TimeIntervalDTO> getIntervals() {
        return intervals;
    }
    /**
     * Selects interval, which includes the given date.
     * @param asOf the date, null is treated as current timestamp.
     * @return
     */
    public TimeIntervalDTO selectAsOf(Date asOf) {
        if (intervals.isEmpty()) {
            return null;
        }

        Date point = asOf == null ? new Date() : asOf;
        for (TimeIntervalDTO interval : intervals) {
            if (interval.isInRange(point)) {
                return interval;
            }
        }

        return null;
    }

    public Keys<?, ?> getKeys() {
        return keys;
    }
}
