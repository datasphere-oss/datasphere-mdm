
package org.datasphere.mdm.core.type.job.impl.map;

import java.util.Map;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class MapStringParameterDefinition extends MapJobParameterDefinition<String> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public MapStringParameterDefinition(String name, Map<String, String> values) {
        super(name, JobParameterType.STRING, values);
    }
}
