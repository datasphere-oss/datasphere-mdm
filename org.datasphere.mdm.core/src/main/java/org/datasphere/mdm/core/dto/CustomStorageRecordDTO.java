

package org.datasphere.mdm.core.dto;

import java.util.Date;

/**
 * @author Dmitry Kopin on 28.08.2017.
 * Custom setting dto object
 */
public class CustomStorageRecordDTO {
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
