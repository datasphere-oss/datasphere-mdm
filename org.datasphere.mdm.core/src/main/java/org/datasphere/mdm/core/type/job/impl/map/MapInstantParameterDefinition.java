
package org.datasphere.mdm.core.type.job.impl.map;

import java.time.Instant;
import java.util.Map;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class MapInstantParameterDefinition extends MapJobParameterDefinition<Instant> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public MapInstantParameterDefinition(String name, Map<String, Instant> values) {
        super(name, JobParameterType.INSTANT, values);
    }
}
