

package org.datasphere.mdm.core.type.security;

import java.io.Serializable;


/**
 * The Interface Endpoint.
 */
public interface Endpoint extends Serializable{
	
	/**
	 * Endpoint name.
	 * <ul>
	 * <li>REST</li>
	 * <li>SOAP</li>
	 * </ul>
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Gets the display name.
	 *
	 * @return the display name
	 */
	String getDisplayName();

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	String getDescription();
}
