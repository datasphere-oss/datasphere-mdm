

package org.datasphere.mdm.core.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.core.configuration.CoreConfigurationConstants;
import org.datasphere.mdm.core.service.PasswordPolicyService;
import org.springframework.stereotype.Service;
import org.datasphere.mdm.system.type.annotation.ConfigurationRef;
import org.datasphere.mdm.system.type.configuration.ConfigurationValue;

/**
 * @author Alexey Tsarapkin
 */
@Service
public class PasswordPolicyServiceImpl implements PasswordPolicyService {

    //private static final Logger LOGGER = LoggerFactory.getLogger(PasswordPolicyServiceImpl.class);

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_POLICY_ALLOW_PASSWORD_CHANGE)
    private ConfigurationValue<Boolean> userAllowChangeExpiredPassword;

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_POLICY_PASSWORD_EXPIRATION_NOTIFICATION_PERIOD)
    private ConfigurationValue<Long> notificationPeriodDays;

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_POLICY_PASSWORD_ADMIN_EXPIRATION_DAYS)
    private ConfigurationValue<Long> adminExpirationDays;

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_POLICY_PASSWORD_USER_EXPIRATION_DAYS)
    private ConfigurationValue<Long> userExpirationDays;

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_POLICY_PASSWORD_MIN_LENGTH)
    private ConfigurationValue<Long> minLength;

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_POLICY_REGEXP)
    private ConfigurationValue<String> regexpTemplate;

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_POLICY_REGEXP_EXAMPLE)
    private ConfigurationValue<String> regexpExample;

    @ConfigurationRef(CoreConfigurationConstants.PROPERTY_POLICY_CHECK_REPETITIONS_COUNT)
    private ConfigurationValue<Long> lastRepeat;

    /**
     * {@inheritDoc}
     */
    @Override
    public String regexpExample() {
        return regexpExample.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean regexpMatching(String password) {

        if (!regexpTemplate.hasValue()) {
            return Boolean.TRUE;
        }

        return password.matches(regexpTemplate.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNotificationPeriodDays() {
        return notificationPeriodDays.getValue().intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLengthEnough(String password) {
        return minLength.hasValue() ? StringUtils.length(password) >= minLength.getValue() : Boolean.TRUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAllowChangeExpiredPassword(boolean admin) {
        return admin ? Boolean.TRUE : userAllowChangeExpiredPassword.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExpired(Instant from, boolean admin) {
        if (isUnlimitedExpiration(admin)) {
            return Boolean.FALSE;
        }

        Duration d = getRemainingExpiration(LocalDateTime.ofInstant(from, ZoneOffset.UTC), admin);

        return d.getSeconds() < 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Duration getRemainingExpiration(LocalDateTime from, boolean admin) {
        LocalDateTime expirationTime = from.plusDays(admin ? adminExpirationDays.getValue() : userExpirationDays.getValue());
        return Duration.between(LocalDateTime.now(), expirationTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getLastRepeatCount() {
        return lastRepeat.getValue().intValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUnlimitedExpiration(boolean admin) {
        return admin
                ? !adminExpirationDays.hasValue() || adminExpirationDays.getValue() <= 0
                : !userExpirationDays.hasValue() || userExpirationDays.getValue() <= 0;
    }

}
