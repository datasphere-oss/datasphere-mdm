
package org.datasphere.mdm.core.dto;

import org.datasphere.mdm.core.type.security.Endpoint;

/**
 * The Class UserAPIDTO.
 * @author ilya.bykov
 */
public class UserEndpointDTO extends BaseSecurityDTO implements Endpoint {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The name. */
	private String name;

	/** The display name. */
	private String displayName;

	/** The description. */
	private String description;

	public UserEndpointDTO(String name, String displayName, String description) {
	    super();
	    this.name = name;
	    this.displayName = displayName;
	    this.description = description;
	}

	public UserEndpointDTO() {
	    super();
	}
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
    public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the display name.
	 *
	 * @return the display name
	 */
	@Override
    public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the display name.
	 *
	 * @param displayName the new display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Override
    public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
