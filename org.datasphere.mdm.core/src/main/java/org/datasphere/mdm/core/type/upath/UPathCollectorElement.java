
package org.datasphere.mdm.core.type.upath;

/**
 * @author Mikhail Mikhailov on Feb 25, 2021
 * Collector element.
 */
public interface UPathCollectorElement extends UPathElement {
    /**
     * {@inheritDoc}
     */
    @Override
    default boolean isCollecting() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    default UPathCollectorElement getCollector() {
        return this;
    }
}
