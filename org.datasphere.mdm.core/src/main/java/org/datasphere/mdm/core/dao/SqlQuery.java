

package org.datasphere.mdm.core.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * mark field in Repository classes like auto set from sql.properties
 *
 * @author maria.chistyakova
 * @since 22.05.2019
 */
@Retention(RetentionPolicy.RUNTIME) // Make this annotation accessible at runtime via reflection.
@Target({ElementType.FIELD})
public @interface SqlQuery {

    // for proguard mode
    String value();
}
