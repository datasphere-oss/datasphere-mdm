
package org.datasphere.mdm.core.service;

import java.nio.file.Path;
import java.util.List;

/**
 * @author maria.chistyakova
 * @since 26.04.2020
 */
public interface ImportStructureService<T> {

    /**
     * validate structure
     *
     * @param rootFolder
     * @param fileName
     * @return validation error messages
     */
    List<String> validateImportedStructure(Path rootFolder, String fileName);

    T prepareUploadData(Path rootFolder, Path pathToZipFile, boolean isOverride, String fileName, String id);

}
