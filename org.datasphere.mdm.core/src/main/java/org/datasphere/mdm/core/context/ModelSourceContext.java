
package org.datasphere.mdm.core.context;

import org.datasphere.mdm.core.type.model.ModelImplementation;
import org.datasphere.mdm.core.type.model.ModelSource;

/**
 * @author Mikhail Mikhailov on Oct 8, 2020
 * General context type for transportation of model source of a specific type.
 * This is used for {@link ModelImplementation#allow(ModelSource)} calls and can be used for other purposes.
 */
public interface ModelSourceContext<X extends ModelSource> extends ModelIdentityContext {
    /**
     * Gets model source.
     * @return model source
     */
    X getSource();
}
