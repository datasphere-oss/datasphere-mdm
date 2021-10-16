

package org.datasphere.mdm.core.po;

import java.util.Date;

/**
 * @author Dmitry Kopin on 25.08.2017.
 */
public class CustomStorageRecordPO {
    /**
     * Field Setting owner.
     */
    public static final String FIELD_USER_NAME = "user_name";
    /**
     * Field Setting key.
     */
    public static final String FIELD_KEY = "key";
    /**
     * Field Setting value.
     */
    public static final String FIELD_VALUE = "value";
    /**
     * Field Setting update date.
     */
    public static final String FIELD_UPDATE_DATE = "update_date";
    /**
     * Setting owner
     */
    private String user;
    /**
     * Setting key
     */
    private String key;
    /**
     * Setting value
     */
    private String value;
    /**
     * Setting update date
     */
    private Date updateDate;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
