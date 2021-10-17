

package org.datasphere.mdm.core.type.search;

import javax.annotation.Nonnull;

import org.datasphere.mdm.search.type.IndexType;

/**
 * @author Mikhail Mikhailov on Oct 11, 2019
 */
public enum AuditIndexType implements IndexType {
    /**
     * Classifier data.
     */
    AUDIT("audit_element");
    /**
     * Index name.
     */
    public static final String INDEX_NAME = "audit";
    /**
     * Name of type
     */
    private final String type;
    /**
     * Constructor.
     * @param type the name of the type
     */
    AuditIndexType(String type) {
        this.type = type;
    }
    /**
     * @return name of type
     */
    @Nonnull
    @Override
    public String getName() {
        return type;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRelated(IndexType searchType) {
        return false;
    }
}
