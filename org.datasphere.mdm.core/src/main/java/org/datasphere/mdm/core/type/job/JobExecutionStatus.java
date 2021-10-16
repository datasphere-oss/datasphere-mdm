

package org.datasphere.mdm.core.type.job;

import javax.annotation.Nullable;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Mikhail Mikhailov on Jun 25, 2021
 * Same as SB BatchStatus.
 */
public enum JobExecutionStatus {
    /**
     * Finished/complete.
     */
    COMPLETED,
    /**
     * Currently starting.
     */
    STARTING,
    /**
     * Started and running.
     */
    STARTED,
    /**
     * Stopping upon request.
     */
    STOPPING,
    /**
     * Stopped upon request.
     */
    STOPPED,
    /**
     * Failed badly.
     */
    FAILED,
    /**
     * Abandoned.
     */
    ABANDONED,
    /**
     * Unknown.
     */
    UNKNOWN;
    /**
     * Non-throwing, case insensetive valueOf(String).
     * @param v the string
     * @return {@linkplain JobExecutionStatus} or null
     */
    @Nullable
    public static JobExecutionStatus fromValue(final String v) {

        for (int i = 0; i < JobExecutionStatus.values().length; i++) {

            final JobExecutionStatus c = JobExecutionStatus.values()[i];
            if (StringUtils.equalsIgnoreCase(v, c.name())) {
                return c;
            }

        }

        return null;
    }
    /**
     * Tells, whether this job is in active state.
     * @return true, if job execution is active
     */
    public boolean isActive() {
        return this == STARTING || this == STARTED || this == STOPPING;
    }
}
