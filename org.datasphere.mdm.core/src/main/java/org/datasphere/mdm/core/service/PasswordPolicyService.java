

package org.datasphere.mdm.core.service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author Alexey Tsarapkin
 */
public interface PasswordPolicyService {
    /**
     *  show user regexp example
     * @return regexpExample
     */
    String regexpExample();

    /**
     * Notification period
     * @return NotificationPeriodDays
     */
    Integer getNotificationPeriodDays();

    /**
     * check password length
     * @param password
     * @return boolean
     */
    boolean isLengthEnough(String password);

    /**
     * Check expired password change allowed
     * @param admin
     * @return boolean
     */
    boolean isAllowChangeExpiredPassword(boolean admin);

    /**
     * Check password expired
     * @param from - date of last created or updated password
     * @param admin
     * @return boolean
     */
    boolean isExpired(Instant from, boolean admin);

    /**
     * Remaining expiration period
     * @param from - date of last created or updated password
     * @param admin
     * @return
     */
    Duration getRemainingExpiration(LocalDateTime from, boolean admin);

    /**
     * Apply regexp
     * @param password
     * @return
     */
    boolean regexpMatching(String password);

    /**
     * Count of last passwords for check repeating
     * @return
     */
    Integer getLastRepeatCount();

    /**
     * check nlimitedExpiration
     * @return
     */
    boolean isUnlimitedExpiration(boolean admin);
}
