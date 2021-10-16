

package org.datasphere.mdm.core.service;

import java.util.List;

import org.datasphere.mdm.core.dto.CustomStorageRecordDTO;

/**
 * @author Dmitry Kopin on 28.08.2017.
 * Service for work with custom settings
 */
public interface CustomStorageService {
    /**
     * Create list of records in custom storage
     * @param customStorageRecords list of {@link CustomStorageRecordDTO}
     */
    boolean createRecords(List<CustomStorageRecordDTO> customStorageRecords);
    /**
     * Update list of records in custom storage
     * @param customStorageRecords list of {@link CustomStorageRecordDTO}
     */
    boolean updateRecords(List<CustomStorageRecordDTO> customStorageRecords);
    /**
     * Delete list of records in custom storage
     * @param customStorageRecords list of {@link CustomStorageRecordDTO}
     */
    boolean deleteRecords(List<CustomStorageRecordDTO> customStorageRecords);
    /**
     * Delete list of record in custom storage by key
     * @param key key
     */
    boolean deleteRecordsByKey(String key);
    /**
     * Delete list of record in custom storage by user name
     * @param userName user name
     */
    boolean deleteRecordsByUserName(String userName);
    /**
     * Get list of records from custom storage by user name
     * @param userName user name
     * @return list of records
     */
    List<CustomStorageRecordDTO> getRecordsByUserName(String userName);
    /**
     * Get list of records from custom storage by key
     * @param key key
     * @return list of records
     */
    List<CustomStorageRecordDTO> getRecordsByKey(String key);
    /**
     * Get list of records from custom storage by user name and key
     * @param userName user name
     * @param key key
     * @return list of records
     */
    List<CustomStorageRecordDTO> getRecordsByKeyAndUser(String key, String userName);
}
