
package org.datasphere.mdm.core.type.job.impl.map;

import java.util.Map;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class MapIntegerParameterDefinition extends MapJobParameterDefinition<Long> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public MapIntegerParameterDefinition(String name, Map<String, Long> values) {
        super(name, JobParameterType.INTEGER, values);
    }
}
