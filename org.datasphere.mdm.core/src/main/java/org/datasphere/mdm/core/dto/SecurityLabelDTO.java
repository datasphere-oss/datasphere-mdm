

package org.datasphere.mdm.core.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.datasphere.mdm.core.type.security.SecurityLabel;
import org.datasphere.mdm.core.type.security.SecurityLabelAttribute;

/**
 * Security label data transfer object.
 *
 * @author ilya.bykov
 *
 */
public class SecurityLabelDTO extends BaseSecurityDTO implements SecurityLabel, Serializable {
	/**
     * SVUID.
     */
    private static final long serialVersionUID = 7787793632986876965L;
    /**
	 * Security label name.
	 */
	private String name;
	/**
	 * Display name.
	 */
	private String displayName;
	/**
	 * Description.
	 */
	private String description;
	/**
	 * Security label attributes.
	 */
	private List<SecurityLabelAttribute> attributes;

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the displayName
	 */
	@Override
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the attributes
	 */
	@Override
	public List<SecurityLabelAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new ArrayList<>();
		}
		return attributes;
	}

	/**
	 * @param attributes
	 *            the attributes to set
	 */
	public void setAttributes(final List<SecurityLabelAttribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
