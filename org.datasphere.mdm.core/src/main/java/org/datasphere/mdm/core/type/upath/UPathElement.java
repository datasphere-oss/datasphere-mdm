
package org.datasphere.mdm.core.type.upath;

/**
 * @author Mikhail Mikhailov
 * UPath element.
 */
public interface UPathElement {
    /**
     * Gets source element (input string).
     * @return the source element (input string)
     */
    String getElement();
    /**
     * Gets exact element type.
     * @return the exact element type
     */
    UPathElementType getType();
    /**
     * Gets true if filtering, false for collecting
     * @return true if filtering, false for collecting
     */
    default boolean isFiltering() {
        return false;
    }
    /**
     * Gets filter element view or null, if this is not a filtering element.
     * @return filter element view or null, if this is not a filtering element
     */
    default UPathFilterElement getFilter() {
        return null;
    }
    /**
     * Gets true if collecting, false for filtering
     * @return true if collecting, false for filtering
     */
    default boolean isCollecting() {
        return false;
    }
    /**
     * Gets collector element view or null, if this is not a filtering element.
     * @return collector element view or null, if this is not a filtering element
     */
    default UPathCollectorElement getCollector() {
        return null;
    }
}