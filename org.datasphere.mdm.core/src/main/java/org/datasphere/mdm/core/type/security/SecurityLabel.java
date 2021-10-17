

package org.datasphere.mdm.core.type.security;

import java.util.List;

/**
 * @author Denis Kostovarov
 */
public interface SecurityLabel {

	String getName();

	String getDisplayName();

	List<SecurityLabelAttribute> getAttributes();

	String getDescription();
}
