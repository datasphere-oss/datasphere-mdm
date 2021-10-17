

package org.datasphere.mdm.data.context;

/**
 * @author Mikhail Mikhailov
 * Alias of a code attribute.
 */
public class CodeAttributeAlias {

    /**
     * Lookup attribute name, which is an alternative of the code attribute (alias of the code attribute).
     */
    private final String aliasAttributeName;
    /**
     * Name of the record attribute, containing alias value.
     */
    private final String recordAttributeName;
    /**
     * Constructor.
     */
    public CodeAttributeAlias(String aliasAttributeName, String recordAttributeName) {
        super();
        this.aliasAttributeName = aliasAttributeName;
        this.recordAttributeName = recordAttributeName;
    }
    /**
     * @return the aliasAttributeName
     */
    public String getAliasAttributeName() {
        return aliasAttributeName;
    }
    /**
     * @return the recordAttributeName
     */
    public String getRecordAttributeName() {
        return recordAttributeName;
    }
}
