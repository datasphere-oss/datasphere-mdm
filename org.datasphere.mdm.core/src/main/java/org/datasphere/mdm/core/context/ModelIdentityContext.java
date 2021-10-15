
package org.datasphere.mdm.core.context;

import org.datasphere.mdm.system.context.StorageSpecificContext;
import org.datasphere.mdm.system.type.pipeline.PipelineInput;

/**
 * @author Mikhail Mikhailov on Oct 26, 2020
 * Basic model identity.
 */
public interface ModelIdentityContext extends StorageSpecificContext, PipelineInput {
    /**
     * Gets the model instance id, the operation is for (if the model type supports instance ids).
     * @return model instance id
     */
    String getInstanceId();
    /**
     * Returns the model type id (the id supplied with model type descriptor for this model type).
     * @return model type id
     */
    String getTypeId();
}
