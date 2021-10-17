

/**
 *
 */
package org.datasphere.mdm.data.dto;

import java.util.List;
import java.util.Map;

/**
 * @author Mikhail Mikhailov
 * Relation(s) DTO.
 */
public interface RelationsDTO <T extends RelationDTO> {
    /**
     * Gets relation state for a number of relations.
     * @return map
     */
    Map<RelationStateDTO, List<T>> getRelations();
}
