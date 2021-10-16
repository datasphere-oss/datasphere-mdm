
/**
 * Date: 05.07.2016
 */

package org.datasphere.mdm.core.po.security;

/**
 * FIXDOC: add file description.
 *
 * @author amagdenko
 */
public class UserPropertyPO extends BaseSecurityPO {
    private Long id;
    private String name;
    private boolean required;
    private String displayName;
    private boolean readOnly;
    /**
     * type of ui control defined by integration developers.
     */
    private String fieldType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public UserPropertyPO setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
        return this;
    }

    public String getFieldType() {
        return fieldType;
    }

    public UserPropertyPO setFieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public enum FieldColumns {
        ID,
        REQUIRED,
        NAME,
        DISPLAY_NAME,
        READ_ONLY,
        FIELD_TYPE
    }
}
