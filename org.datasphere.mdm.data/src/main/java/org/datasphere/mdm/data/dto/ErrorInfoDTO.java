

package org.datasphere.mdm.data.dto;

import java.io.Serializable;

public class ErrorInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Type {
        VALIDATION_ERROR,
        INTERNAL_ERROR,
        AUTHENTICATION_ERROR,
        AUTHORIZATION_ERROR,
        USER_ALREADY_EXIST,
        USER_CANNOT_BE_DEACTIVATED
    }

    public enum Severity{
    	LOW("Низкая"),
    	NORMAL("Средняя"),
    	HIGH("Высокая"),
    	CRITICAL("Максимальная");

        private String displayName;

        Severity(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    private Type type;
    private Severity severity;
    private String errorCode;

    private String userMessage;

    private String userMessageDetails;

    public ErrorInfoDTO() {
        super();
    }

    public ErrorInfoDTO(Type type) {
        if (type != null) {
            this.type = type;
        }
    }

    public String getType() {
        if (type != null) {
            return type.name();
        }
        return null;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }


    public String getUserMessageDetails() {
        return userMessageDetails;
    }

    public ErrorInfoDTO setUserMessageDetails(String userMessageDetails) {
        this.userMessageDetails = userMessageDetails;
        return this;
    }

	/**
	 * @return the severity
	 */
	public Severity getSeverity() {
		if(this.severity==null){
			return Severity.NORMAL;
		}
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
}
