
package org.datasphere.mdm.core.type.job.impl.map;

import java.time.LocalTime;
import java.util.Map;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class MapTimeParameterDefinition extends MapJobParameterDefinition<LocalTime> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public MapTimeParameterDefinition(String name, Map<String, LocalTime> values) {
        super(name, JobParameterType.TIME, values);
    }
}
