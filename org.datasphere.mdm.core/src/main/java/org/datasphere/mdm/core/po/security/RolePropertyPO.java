

/**
 * Date: 05.07.2016
 */

package org.datasphere.mdm.core.po.security;

/**
 * FIXDOC: add file description.
 *
 * @author amagdenko
 */
public class RolePropertyPO extends BaseSecurityPO {
    private Long id;
    private String name;
    private boolean required;
    private String displayName;

    /*
    by UN-12831
    type of ui control defined by integration developers.
    */
    private String fieldType;

    // by UN-12188
    private boolean readOnly;


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

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }


    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
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
