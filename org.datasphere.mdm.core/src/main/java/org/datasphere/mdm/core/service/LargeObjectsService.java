

package org.datasphere.mdm.core.service;

import org.datasphere.mdm.core.context.DeleteLargeObjectContext;
import org.datasphere.mdm.core.context.FetchLargeObjectContext;
import org.datasphere.mdm.core.context.UpsertLargeObjectContext;
import org.datasphere.mdm.core.dto.LargeObjectResult;

/**
 * @author Dmitry Kopin on 26.12.2017.
 */
public interface LargeObjectsService {
    /**
     * Gets large object input stream according to context specification.
     * @param ctx the context
     * @return {@link LargeObjectResult} instance
     */
    LargeObjectResult fetchLargeObject(FetchLargeObjectContext ctx);
    /**
     * Saves large object data.
     * @param ctx the context
     * @return true if successful, false otherwise
     */
    LargeObjectResult saveLargeObject(UpsertLargeObjectContext ctx);
    /**
     * Deletes large object data.
     * @param ctx the context
     * @return true if successful, false otherwise
     */
    boolean deleteLargeObject(DeleteLargeObjectContext ctx);
    /**
     * Check large object exist
     * @param ctx context
     * @return return true if exist, else false
     */
    boolean checkExistLargeObject(FetchLargeObjectContext ctx);
}
