

package org.datasphere.mdm.core.dao;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.datasphere.mdm.core.po.lob.LargeObjectPO;

/**
 * @author Mikhail Mikhailov
 * Large objects DAO.
 */
public interface LargeObjectsDAO {
    /**
     * Fetch large object.
     * @param id the id
     * @param isBinary character or binary
     * @return object or null
     */
    LargeObjectPO loadLargeObject(UUID id, boolean isBinary);
    /**
     * Upsert large object.
     * @param lob the object
     * @return true if successful, false otherwise
     * @throws IOException
     */
    boolean upsertLargeObject(LargeObjectPO lob) throws IOException;
    /**
     * Sets a record active (submits an attachment).
     * @param spec activation spec (objects)
     * @return true if successful, false otherwise
     */
    boolean submitLargeObjects(Collection<LargeObjectPO> spec);
    /**
     * Deletes a large object.
     * @param id the id
     * @param isBinary character or binary
     * @return true if successful, false otherwise
     */
    boolean wipeLargeObject(UUID id, boolean isBinary);
    /**
     * Deletes a large object.
     * @param subject the subject id
     * @param tags the tags
     * @param isBinary character or binary
     * @return true if successful, false otherwise
     */
    boolean wipeLargeObjects(String subject, String[] tags, boolean isBinary);
    /**
     * Returns true, if character or binary object with the given ID exists.
     * @param id the ID to check
     * @param isBinary type indicator
     * @return true, if character or binary object with the given ID exists
     */
    boolean checkLargeObject(UUID id, boolean isBinary);
    /**
     * Clean unused binary data
     * @param maxLifetime max lifetime for binary data
     * @return count of removed messages
     */
    long cleanUnusedBinaryData(long maxLifetime);
    /**
     * Drops both character and binary data for the given subject IDs (record origin IDs, event IDs, etc.)
     * @param subjects the subject IDs
     * @return count of deleted records
     */
    long cleanForSubjectIds(List<String> subjects);
}
