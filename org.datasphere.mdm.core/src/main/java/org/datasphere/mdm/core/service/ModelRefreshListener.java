
package org.datasphere.mdm.core.service;

import org.datasphere.mdm.core.context.ModelRefreshContext;

/**
 * @author Mikhail Mikhailov on Nov 2, 2020
 */
public interface ModelRefreshListener {
    /**
     * Refreshes a model part.
     * @param refresh the info
     */
    void refresh(ModelRefreshContext refresh);
    /**
     * Returns supported type id, so the MMS can filter refresh events.
     * @return supported type id
     */
    String getTypeId();
}
