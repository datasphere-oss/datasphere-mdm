

/**
 * Date: 05.07.2016
 */

package org.datasphere.mdm.core.po.security;

/**
 * FIXDOC: add file description.
 *
 * @author amagdenko
 */
public class UserPropertyValuePO extends BaseSecurityPO {
    private Long id;
    private Long userId;
    private UserPropertyPO property;
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserPropertyPO getProperty() {
        return property;
    }

    public void setProperty(UserPropertyPO property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public enum FieldColumns {
        ID,
        USER_ID,
        PROPERTY_ID,
        VALUE
    }
}
