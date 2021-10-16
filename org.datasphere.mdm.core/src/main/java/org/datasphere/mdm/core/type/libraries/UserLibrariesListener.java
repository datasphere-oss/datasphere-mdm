

package org.datasphere.mdm.core.type.libraries;

/**
 * @author Mikhail Mikhailov on Feb 1, 2021
 * UL updates listener.
 */
public interface UserLibrariesListener {
    /**
     * Library with the given properties was either updated or inserted.
     * @param storage the storage id
     * @param name the name
     * @param version the version
     */
    void libraryUpserted(String storage, String name, String version);
    /**
     * Library with the given properties was removed.
     * @param storage the storage id
     * @param name the name
     * @param version the version
     */
    void libraryRemoved(String storage, String name, String version);
}
