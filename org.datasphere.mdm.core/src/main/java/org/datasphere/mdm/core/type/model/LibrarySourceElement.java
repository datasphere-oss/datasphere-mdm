
package org.datasphere.mdm.core.type.model;

/**
 * @author Mikhail Mikhailov on Feb 16, 2021
 * Marks implementation as a library sourced element.
 */
public interface LibrarySourceElement {
    /**
     * Returns the name of the jar/zip/whatever file, that contains the implementing class.
     * @return the name of the jar/zip/whatever file, that contains the implementing class.
     */
    String getLibrary();
    /**
     * Version of the library.
     * @return version of the library.
     */
    String getVersion();
}
