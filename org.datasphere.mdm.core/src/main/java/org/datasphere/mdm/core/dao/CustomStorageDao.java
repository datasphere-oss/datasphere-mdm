

package org.datasphere.mdm.core.dao;

import javax.annotation.Nonnull;

import org.datasphere.mdm.core.po.CustomStorageRecordPO;

import java.util.List;

/**
 * @author Dmitry Kopin on 25.08.2017.
 *         Dao for work with custom storage
 */
public interface CustomStorageDao {

    /**
     * Create list of records in custom storage
     * @param customStorageRecords list of {@link CustomStorageRecordPO}
     */
    void createRecords(@Nonnull List<CustomStorageRecordPO> customStorageRecords);
    /**
     * Update list of records in custom storage
     * @param customStorageRecords list of {@link CustomStorageRecordPO}
     */
    void updateRecords(@Nonnull List<CustomStorageRecordPO> customStorageRecords);
    /**
     * Delete list of records in custom storage
     * @param customStorageRecords list of {@link CustomStorageRecordPO}
     */
    void deleteRecords(List<CustomStorageRecordPO> customStorageRecords);
    /**
     * Delete list of record in custom storage by key
     * @param key key
     */
    void deleteRecordsByKey(String key);
    /**
     * Delete list of record in custom storage by user name
     * @param userName user name
     */
    void deleteRecordsByUserName(String userName);
    /**
     * Get list of records from custom storage by user name
     * @param userName user name
     * @return list of records
     */
    List<CustomStorageRecordPO> getRecordsByUserName(String userName);
    /**
     * Get list of records from custom storage by key
     * @param key key
     * @return list of records
     */
    List<CustomStorageRecordPO> getRecordsByKey(String key);
    /**
     * Get list of records from custom storage by user name and key
     * @param userName user name
     * @param key key
     * @return list of records
     */
    List<CustomStorageRecordPO> getRecordsByKeyAndUser(String key, String userName);

}
