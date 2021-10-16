

package org.datasphere.mdm.core.type.data.impl;

import org.datasphere.mdm.core.type.data.Attribute;
import org.datasphere.mdm.core.type.data.DataRecord;

/**
 * @author Mikhail Mikhailov
 * Abstract attribute name holder.
 */
public abstract class AbstractAttribute implements Attribute {
    /**
     * Attribute name.
     */
    private String name;
    /**
     * Record link.
     */
    private DataRecord record;
    /**
     * Special serialization constructor. Schould not be used otherwise.
     */
    protected AbstractAttribute() {
        super();
    }
    /**
     * Constructor.
     * @param name the name of the attribute.
     */
    protected AbstractAttribute(String name) {
        super();
        this.name = name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * Sets the attribute name. Used for serialization. Should not be used otherwise.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DataRecord getRecord() {
        return record;
    }
    /**
     * @param record the record to set
     */
    @Override
    public void setRecord(DataRecord record) {
        this.record = record;
    }
}
