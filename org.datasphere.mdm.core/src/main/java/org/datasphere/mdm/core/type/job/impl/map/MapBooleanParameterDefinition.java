
package org.datasphere.mdm.core.type.job.impl.map;

import java.util.Map;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class MapBooleanParameterDefinition extends MapJobParameterDefinition<Boolean> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public MapBooleanParameterDefinition(String name, Map<String, Boolean> values) {
        super(name, JobParameterType.BOOLEAN, values);
    }
}
