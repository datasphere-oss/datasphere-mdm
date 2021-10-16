

/**
 *
 */
package org.datasphere.mdm.core.dto;

import java.util.Date;

/**
 * @author Mikhail Mikhailov
 *
 */
public class UserEventDTO {

    /**
     * Event id.
     */
    private final String id;
    /**
     * Create date.
     */
    private Date createDate;
    /**
     * Created by.
     */
    private String createdBy;
    /**
     * Binary data ID. Read only.
     */
    private String binaryDataId;
    /**
     * Binary data ID. Read only.
     */
    private String characterDataId;
    /**
     * Type.
     */
    private String type;
    /**
     * Content.
     */
    private String content;
    /**
     * Details.
     */
    private String details;

    /**
     * Constructor.
     * @param id event id
     */
    public UserEventDTO(String id) {
        super();
        this.id = id;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }


    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }


    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }


    /**
     * @return the binaryDataId
     */
    public String getBinaryDataId() {
        return binaryDataId;
    }


    /**
     * @param binaryDataId the binaryDataId to set
     */
    public void setBinaryDataId(String binaryDataId) {
        this.binaryDataId = binaryDataId;
    }


    /**
     * @return the characterDataId
     */
    public String getCharacterDataId() {
        return characterDataId;
    }


    /**
     * @param characterDataId the characterDataId to set
     */
    public void setCharacterDataId(String characterDataId) {
        this.characterDataId = characterDataId;
    }


    /**
     * @return the type
     */
    public String getType() {
        return type;
    }


    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }


    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }


    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }


    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }


    /**
     * @param details of content
     */
    public void setDetails(String details) {
        this.details = details;
    }

}
