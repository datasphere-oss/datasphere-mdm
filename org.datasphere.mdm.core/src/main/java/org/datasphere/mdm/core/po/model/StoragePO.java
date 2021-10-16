

/**
 *
 */
package org.datasphere.mdm.core.po.model;

import org.datasphere.mdm.core.po.AbstractObjectPO;

/**
 * @author Mikhail Mikhailov
 * Storage PO.
 */
public class StoragePO extends AbstractObjectPO {
    /**
     * Table name.
     */
    public static final String TABLE_NAME = "storage";
    /**
     * ID.
     */
    public static final String FIELD_ID = "id";
    /**
     * Name.
     */
    public static final String FIELD_DECSRIPTION = "description";
    /**
     * ID.
     */
    protected String id;
    /**
     * Storage id.
     */
    protected String description;
    /**
     * Constructor.
     */
    public StoragePO() {
        super();
    }
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
