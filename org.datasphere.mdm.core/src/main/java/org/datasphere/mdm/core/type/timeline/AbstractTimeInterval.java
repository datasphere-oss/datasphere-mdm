

package org.datasphere.mdm.core.type.timeline;

import java.util.Date;

import org.datasphere.mdm.core.type.calculables.Calculable;
import org.datasphere.mdm.core.type.calculables.CalculationResult;

/**
 * @author Mikhail Mikhailov
 * Common part of the interval API.
 */
public abstract class AbstractTimeInterval<T extends Calculable> implements TimeInterval<T> {
    /**
     * Valid from.
     */
    protected Date validFrom;
    /**
     * Valid to.
     */
    protected Date validTo;
    /**
     * The calculation result, if applicable.
     */
    protected CalculationResult<T> calculationResult;
    /**
     * Interval is active or not.
     */
    protected boolean active;

    /**
     * Constructor.
     */
    protected AbstractTimeInterval() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Date getValidFrom() {
        return validFrom;
    }
    /**
     * Sets the validity interval start.
     * @param validFrom the validFrom to set
     */
    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Date getValidTo() {
        return validTo;
    }
    /**
     * Sets the validity interval end.
     * @param validTo the validTo to set
     */
    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public<V extends CalculationResult<T>> V getCalculationResult() {
        return (V) calculationResult;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCalculationResult(CalculationResult<T> result) {
        this.calculationResult = result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return active;
    }
}
