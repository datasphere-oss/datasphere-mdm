

package org.datasphere.mdm.core.type.model;

import java.time.OffsetDateTime;

/**
 * @author Mikhail Mikhailov on Jan 25, 2021
 * Timestaps, who changed.
 */
public interface CreateUpdateElement {
    /**
     * Gets create date.
     * @return create date
     */
    OffsetDateTime getCreateDate();
    /**
     * Gets name of the user, who created this definition.
     * @return name of the user, who created this definition.
     */
    String getCreatedBy();
    /**
     * Gets last update date.
     * @return last update date
     */
    OffsetDateTime getUpdateDate();
    /**
     * Gets name of the user, who updated this definition.
     * @return name of the user, who updated this definition.
     */
    String getUpdatedBy();
}
