

package org.datasphere.mdm.core.type.model;

/**
 * @author Mikhail Mikhailov
 * Measured value adjuster.
 */
public interface MeasuredValueElement {
    /**
     * Measured value category id - mass, distance, acceleration, denisity, etc.
     * @return id the category id
     */
    String getCategoryId();
    /**
     * Default unit of the category id - kylograms, pounds, picogram for category "mass"..
     * @return id the default unit id
     */
    String getDefaultUnitId();
}
