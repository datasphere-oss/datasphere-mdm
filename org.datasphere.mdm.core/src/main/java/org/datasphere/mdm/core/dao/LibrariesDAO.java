
package org.datasphere.mdm.core.dao;

import java.util.List;

import org.datasphere.mdm.core.po.LibraryPO;

/**
 * @author Mikhail Mikhailov on Oct 2, 2020
 * DQ model DAO.
 */
public interface LibrariesDAO {
    /**
     * Loads current versions of libraries set for a storage id.
     * @param storageId the storage id
     * @param name the name
     * @param mimeType the MIME type
     * @param latest only latest versions, if true, all otherwise
     * @param withData load data too
     * @return libraries set or empty list.
     */
    List<LibraryPO> load(String storageId, String name, String mimeType, boolean latest, boolean withData);
    /**
     * Loads current (latest by create date) version of a specific library.
     * @param storageId the storage id
     * @param name the name of a particular library
     * @param withData load also data (or not)
     * @return library or null.
     */
    LibraryPO load(String storageId, String name, boolean withData);
    /**
     * Loads specific version of a specific library.
     * @param storageId the storage id
     * @param name the name of a particular library
     * @param version specific version
     * @param withData  load also data (or not)
     * @return library or null.
     */
    LibraryPO load(String storageId, String name, String version, boolean withData);
    /**
     * Saves a new version of DQ model.
     * @param po the PO
     * @param force force update for 'not editable' libraries
     * @return true if successfully saved
     */
    boolean save(LibraryPO po, boolean force);
    /**
     * Deletes a DQ model revision.
     * @param storageId the storage id
     * @param name the name of the library
     * @param version the version
     */
    void remove(String storageId, String name, String version);
}
