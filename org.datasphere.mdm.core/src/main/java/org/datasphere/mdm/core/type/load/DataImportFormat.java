
package org.datasphere.mdm.core.type.load;
/**
 * @author Mikhail Mikhailov on May 13, 2021
 * For now, just designate the base input format.
 * The data in this format will hopefully be mapped analogously to ExchangeFormat for 5.x in the future.
 */
public enum DataImportFormat {
    /*
     * XML, Thrift, Avro, protobuff....
     */
    XLSX(".xlsx"),
    JSON(".json");
    /**
     * File extension.
     */
    private final String extension;
    /**
     * Constructor.
     * @param extension the file extension
     */
    private DataImportFormat(String extension) {
        this.extension = extension;
    }
    /**
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }
}
