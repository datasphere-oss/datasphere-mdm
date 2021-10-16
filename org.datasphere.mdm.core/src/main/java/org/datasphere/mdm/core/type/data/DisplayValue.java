

package org.datasphere.mdm.core.type.data;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Mikhail Mikhailov
 * An attribute, that has a display value.
 */
public interface DisplayValue {
    /**
     * Gets the display value.
     * @return display value
     */
    String getDisplayValue();
    /**
     * Sets the display value.
     * @param displayValue the value to set
     */
    void setDisplayValue(String displayValue);
    /**
     * Tells, whether the attribute has display value set.
     * @return true, if so, false otherwise
     */
    default boolean hasDisplayValue() {
        return StringUtils.isNoneBlank(getDisplayValue());
    }
}
