

/**
 *
 */
package org.datasphere.mdm.data.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Nonnull;

import org.datasphere.mdm.core.type.calculables.CalculationResult;

/**
 * @author Mikhail Mikhailov
 *
 */
public class TimeIntervalDTO {

    /**
     * Contributors.
     */
    private final List<ContributorDTO> contributors = new ArrayList<>(4);
    /**
     * Valid from.
     */
    private final Date validFrom;
    /**
     * Valid to.
     */
    private final Date validTo;
    /**
     * Interval is active or not.
     */
    private final boolean active;
    /**
     * Period id (index onn the time line).
     */
    private final long periodId;

    private final CalculationResult calculationResult;
    /**
     * Constructor.
     * @param validFrom period's validity start timestamp
     * @param validTo period's validity end timestamp
     * @param periodId period id (index onn the time line)
     * @param isActive activity mark
     */
    public TimeIntervalDTO(Date validFrom, Date validTo, long periodId, boolean isActive) {
        this(validFrom, validTo, periodId, isActive, null);
    }

    /**
     * Constructor.
     * @param validFrom period's validity start timestamp
     * @param validTo period's validity end timestamp
     * @param periodId period id (index onn the time line)
     * @param isActive activity mark
     * @param calculationResult calculation Result
     */
    public TimeIntervalDTO(Date validFrom, Date validTo, long periodId, boolean isActive, CalculationResult calculationResult) {
        super();
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.periodId = periodId;
        this.active = isActive;
        this.calculationResult = calculationResult;
    }

    /**
     * @return the contributors
     */
    public List<ContributorDTO> getContributors() {
        return contributors;
    }

    /**
     * @return the validFrom
     */
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * @return the validTo
     */
    public Date getValidTo() {
        return validTo;
    }

    /**
     * @return the periodId
     */
    public long getPeriodId() {
        return periodId;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Tells whether the given date is within range of this time interval.
     * @param asOf the date to check interval against. Must not be null
     * @return true, if included, false otherwise
     */
    public boolean isInRange(@Nonnull Date asOf) {
        boolean left = validFrom == null || validFrom.before(asOf) || validFrom.getTime() == asOf.getTime();
        boolean right = validTo == null || validTo.after(asOf) || validTo.getTime() == asOf.getTime();
        return left && right;
    }

    public CalculationResult getCalculationResult() {
        return calculationResult;
    }
}
