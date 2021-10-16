
package org.datasphere.mdm.core.type.model;

/**
 * @author Mikhail Mikhailov on Oct 8, 2020
 * Model source marker, the model descriptor operates on.
 */
public interface ModelSource {
    /**
     * Gets the model instance id, the source is for.
     * This is either real instance name/id (i. e. classifier name) for models that support multiple instances,
     * or just DEFAULT or null for singleton instances, such as DATA, SOURCE_SYSTEMS, ENUMERATIONS, MEASURE_UNITS etc.
     * @return model instance id
     */
    String getInstanceId();
    /**
     * Returns the model type id from configuration (the id supplied with model type descriptor for this model type).
     * @return model type id
     */
    String getTypeId();
}
