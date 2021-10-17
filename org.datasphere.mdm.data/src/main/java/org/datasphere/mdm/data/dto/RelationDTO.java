

package org.datasphere.mdm.data.dto;

import org.datasphere.mdm.data.type.keys.RelationKeys;

/**
 * Relation DTO common signatures.
 * @author Mikhail Mikhailov
 */
public interface RelationDTO {
    /**
     * Gets keys.
     * @return keys
     */
    RelationKeys getRelationKeys();
}
