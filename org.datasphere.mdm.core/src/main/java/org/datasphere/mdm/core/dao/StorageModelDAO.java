
package org.datasphere.mdm.core.dao;

import java.util.List;

import org.datasphere.mdm.core.po.model.StoragePO;

/**
 * @author Mikhail Mikhailov on Oct 2, 2020
 * Simple storage DAO.
 */
public interface StorageModelDAO {
    /**
     * Loads all available storage ids.
     * @return list of ids
     */
    List<StoragePO> loadAll();
    /**
     * Loads object by id.
     * @param id the id
     * @return storage object
     */
    StoragePO loadById(String id);
    /**
     * Creates a new meta storage entry.
     * @param pothe object to update
     */
    void create(StoragePO po);
    /**
     * Updates a meta storage entry.
     * @param pothe object to update
     */
    void update(StoragePO po);
    /**
     * Removes a storage object.
     * @param id the object's id
     */
    void remove(String id);
}
