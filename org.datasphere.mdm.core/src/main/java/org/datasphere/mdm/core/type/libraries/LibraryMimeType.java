

package org.datasphere.mdm.core.type.libraries;

import java.util.Objects;
import java.util.Set;

import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Mikhail Mikhailov on Jan 31, 2021
 * Supported MIME-types (including custom ones).
 */
public enum LibraryMimeType {
    /**
     * The jar file.
     */
    JAR_FILE("application/x-java-archive", Set.of("application/java-archive"), Set.of(".jar")),
    /**
     * Raw python code as text.
     */
    PYTHON_SOURCE("text/x-python", Set.of(), Set.of(".py")),
    /**
     * Compiled python bytecode.
     */
    PYTHON_CODE("application/x-python-code", Set.of(), Set.of(".pyc", ".pyo")),
    /**
     * Groovy source
     */
    GROOVY_SOURCE("text/x-groovy", Set.of(), Set.of(".groovy"));

    private final String code;

    private final Set<String> codes;

    private final Set<String> extensions;
    /**
     * Constructor.
     * @param codes the MIME type code
     * @param extensions known extensions
     */
    private LibraryMimeType(String main, Set<String> codes, Set<String> extensions) {
        this.code = main;
        this.codes = SetUtils.union(Set.of(main), codes);
        this.extensions = extensions;
    }
    /**
     * Gets the code.
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * Gets known extensions.
     * @return the extensions
     */
    public Set<String> getExtensions() {
        return extensions;
    }
    /**
     * Returns Enum instance, if given extension is known as registered type extension.
     * @param fileName the file name, which extension has to be checked
     * @return instance, if given extension is known as registered type extension
     */
    public static LibraryMimeType fromExtension(String fileName) {

        if (Objects.nonNull(fileName)) {

            String extension = StringUtils.substring(fileName, StringUtils.lastIndexOf(fileName, '.'));
            for (LibraryMimeType lmt : LibraryMimeType.values()) {
                if (lmt.extensions.contains(extension)) {
                    return lmt;
                }
            }
        }

        return null;
    }
    /**
     * Gets enum instance by code value.
     * @param mimeType the MIME type
     * @return enum label or null
     */
    public static LibraryMimeType fromCode(String mimeType) {

        if (Objects.nonNull(mimeType)) {
            for (LibraryMimeType lmt : LibraryMimeType.values()) {
                if (lmt.codes.contains(mimeType)) {
                    return lmt;
                }
            }
        }

        return null;
    }
}
