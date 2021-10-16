

package org.datasphere.mdm.core.type.model;

/**
 * @author Mikhail Mikhailov on Oct 21, 2020
 */
public interface PresentationElement {
    /**
     * True for visibility on dashboard.
     * @return true, if visible on dashboard
     */
    boolean isDashboardVisible();
}
