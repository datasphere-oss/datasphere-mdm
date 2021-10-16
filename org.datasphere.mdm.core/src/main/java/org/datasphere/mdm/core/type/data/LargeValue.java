

package org.datasphere.mdm.core.type.data;

import org.datasphere.mdm.core.type.lob.LargeObjectAcceptance;

/**
 * @author Mikhail Mikhailov
 * Large value type.
 */
public interface LargeValue {
    /**
     * @author Mikhail Mikhailov
     * Large value type.
     */
    public enum ValueType {
        /**
         * Binary large value.
         */
        BLOB,
        /**
         * Charcter large value.
         */
        CLOB
    }
    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    String getId();
    /**
     * Gets the possibly supplied file name.
     * @return name
     */
    String getFileName();
    /**
     * Gets the possibly supplied MIME type.
     * @return MIME type
     */
    String getMimeType();
    /**
     * Gets the size in bytes.
     * @return size in bytes
     */
    long getSize();
    /**
     * Gets LOB value acceptance state.
     * @return LOB value acceptance state
     */
    LargeObjectAcceptance getAcceptance();
    /**
     * Gets the value type.
     * @return type
     */
    ValueType getValueType();
}
