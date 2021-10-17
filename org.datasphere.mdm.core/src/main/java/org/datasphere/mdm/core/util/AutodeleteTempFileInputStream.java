

/**
 *
 */
package org.datasphere.mdm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Mikhail Mikhailov
 * Deletes the temp file upon close.
 */
public class AutodeleteTempFileInputStream extends FileInputStream {
    /**
     * Temp file.
     */
    private File file;
    /**
     * Constructor.
     * @param name
     * @throws FileNotFoundException
     */
    public AutodeleteTempFileInputStream(String name) throws FileNotFoundException {
        this(new File(name));
    }
    /**
     * Constructor.
     * @param file
     * @throws FileNotFoundException
     */
    public AutodeleteTempFileInputStream(File file) throws FileNotFoundException {
        super(file);
        this.file = file;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws IOException {
        try {
            super.close();
        } finally {
            if (file != null) {
                Files.delete(file.toPath());
            }
        }
    }
}
