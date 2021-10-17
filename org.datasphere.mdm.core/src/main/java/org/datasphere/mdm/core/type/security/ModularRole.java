

package org.datasphere.mdm.core.type.security;

import java.util.List;

/**
 * for module security configuration: classifier, jobs, over
 *
 * @author maria.chistyakova
 * @since 05.06.2019
 */
public interface ModularRole<T> {
    Role getRole();

    List<T> getRights();

}
