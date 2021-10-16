
package org.datasphere.mdm.core.dto;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

/**
 * @author Mikhail Mikhailov on May 24, 2021
 * Template result.
 */
public class DataImportTemplateResult {
    /**
     * Template bytes.
     */
    private byte[] bytes;
    /**
     * Constructor.
     */
    public DataImportTemplateResult() {
        super();
    }
    /**
     * Constructor.
     */
    public DataImportTemplateResult(byte[] bytes) {
        super();
        this.bytes = bytes;
    }
    /**
     * Constructor.
     */
    public DataImportTemplateResult(ByteArrayOutputStream bytes) {
        super();
        this.bytes = Objects.isNull(bytes) ? null : bytes.toByteArray();
    }
    /**
     * @return the bytes
     */
    public byte[] getBytes() {
        return bytes;
    }
    /**
     * @param bytes the bytes to set
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
