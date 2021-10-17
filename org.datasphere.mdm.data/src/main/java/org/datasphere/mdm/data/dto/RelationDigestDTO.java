

/**
 *
 */
package org.datasphere.mdm.data.dto;

import java.util.List;

/**
 * @author Mikhail Mikhailov
 *
 */
public class RelationDigestDTO {

    /**
     * Destinations.
     */
    private final List<String> etalonIds;
    /**
     * Total count.
     */
    private final long totalCount;
    /**
     * Constructor.
     */
    public RelationDigestDTO(List<String> etalonIds, long totalCount) {
        this.etalonIds = etalonIds;
        this.totalCount = totalCount;
    }

    /**
     * @return the etalonIds
     */
    public List<String> getEtalonIds() {
        return etalonIds;
    }

    /**
     * @return the totalCount
     */
    public long getTotalCount() {
        return totalCount;
    }

}
