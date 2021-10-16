

package org.datasphere.mdm.core.type.data;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Mikhail Mikhailov
 * An attribute, which is a link to a code attribute.
 */
public interface CodeLinkValue {
    /**
     * Gets the link etalon id.
     * @return etalon id
     */
    String getLinkEtalonId();
    /**
     * Sets the link id.
     * @param id the id to set
     */
    void setLinkEtalonId(String id);
    /**
     * Tells, whether this attribute has link etalon id.
     * @return true, if so, false otherwise
     */
    default boolean hasLinkEtalonId() {
        return StringUtils.isNoneBlank(getLinkEtalonId());
    }
}
