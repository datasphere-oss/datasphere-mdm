
package org.datasphere.mdm.core.type.job.impl.map;

import java.util.Map;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class MapNumberParameterDefinition extends MapJobParameterDefinition<Double> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public MapNumberParameterDefinition(String name, Map<String, Double> values) {
        super(name, JobParameterType.NUMBER, values);
    }
}
