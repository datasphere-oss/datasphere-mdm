

package org.datasphere.mdm.core.type.search;

import org.datasphere.mdm.search.type.FieldType;
import org.datasphere.mdm.search.type.IndexField;
import org.datasphere.mdm.system.type.messaging.SystemHeaders;

public enum AuditHeaderField implements IndexField {
    /**
     * The daomain ID.
     */
    DOMAIN(SystemHeaders.DOMAIN.getName(), FieldType.STRING),
    /**
     * Message type ID.
     */
    TYPE(SystemHeaders.TYPE.getName(), FieldType.STRING),
    /**
     * User login.
     */
    LOGIN(SystemHeaders.LOGIN.getName(), FieldType.STRING),
    /**
     * Client IP during the action.
     */
    CLIENT_IP(SystemHeaders.CLIENT_IP.getName(), FieldType.STRING),
    /**
     * Server IP during the action.
     */
    SERVER_IP(SystemHeaders.SERVER_IP.getName(), FieldType.STRING),
    /**
     * The endpoint causing the entry.
     */
    ENDPOINT(SystemHeaders.ENDPOINT.getName(), FieldType.STRING),
    /**
     * Timestamp.
     */
    WHEN_HAPPENED(SystemHeaders.WHEN_HAPPENED.getName(), FieldType.TIMESTAMP),
    /**
     * Success mark.
     */
    SUCCESS(SystemHeaders.SUCCESS.getName(), FieldType.BOOLEAN),
    /**
     * Throwable content, if exists.
     */
    THROWABLE(SystemHeaders.THROWABLE.getName(), FieldType.STRING),
    /**
     * Other params as nested objects.
     */
    PARAMETERS("parameters", FieldType.COMPOSITE),
    /**
     * Parameter name.
     */
    PARAMETER_KEY("key", FieldType.STRING),
    /**
     * Parameter value.
     */
    PARAMETER_VALUE("value", FieldType.STRING);
    /**
     * The field name.
     */
    private final String field;
    /**
     * The type.
     */
    private final FieldType type;
    /**
     * Constructor.
     * @param field the name
     * @param type the type
     */
    AuditHeaderField(String field, FieldType type) {
        this.field = field;
        this.type = type;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return field;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getPath() {
        return this == PARAMETER_KEY || this == PARAMETER_VALUE ? PARAMETERS.getPath() + "." + field : getName();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public FieldType getFieldType() {
        return type;
    }
}
