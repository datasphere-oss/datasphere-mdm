

package org.datasphere.mdm.core.dto;

import java.util.Date;

/**
 * The Class UserWithPasswordRO.
 * @author ilya.bykov
 */
public class UserWithPasswordDTO extends UserDTO {
    /**
     * SVUID.
     */
    private static final long serialVersionUID = -5010154347779875267L;
    
    /** The password. */
    private String password;
    
    /** The password last changed at. */
    private Date passwordLastChangedAt;
    
    /** The password updated by. */
    private String passwordUpdatedBy;

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

	/**
	 * Gets the password last changed at.
	 *
	 * @return the passwordLastChangedAt
	 */
	public Date getPasswordLastChangedAt() {
		return passwordLastChangedAt;
	}

	/**
	 * Sets the password last changed at.
	 *
	 * @param passwordLastChangedAt
	 *            the passwordLastChangedAt to set
	 */
	public void setPasswordLastChangedAt(Date passwordLastChangedAt) {
		this.passwordLastChangedAt = passwordLastChangedAt;
	}

	/**
	 * Gets the password updated by.
	 *
	 * @return the passwordUpdatedBy
	 */
	public String getPasswordUpdatedBy() {
		return passwordUpdatedBy;
	}

	/**
	 * Sets the password updated by.
	 *
	 * @param passwordUpdatedBy
	 *            the passwordUpdatedBy to set
	 */
	public void setPasswordUpdatedBy(String passwordUpdatedBy) {
		this.passwordUpdatedBy = passwordUpdatedBy;
	}
}
