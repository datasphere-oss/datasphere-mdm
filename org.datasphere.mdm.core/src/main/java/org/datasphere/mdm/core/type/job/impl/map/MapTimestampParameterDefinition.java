
package org.datasphere.mdm.core.type.job.impl.map;

import java.time.LocalDateTime;
import java.util.Map;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class MapTimestampParameterDefinition extends MapJobParameterDefinition<LocalDateTime> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public MapTimestampParameterDefinition(String name, Map<String, LocalDateTime> values) {
        super(name, JobParameterType.TIMESTAMP, values);
    }
}
