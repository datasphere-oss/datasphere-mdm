
package org.datasphere.mdm.core.type.job.impl.map;

import java.util.Map;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class MapClobParameterDefinition extends MapJobParameterDefinition<String> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public MapClobParameterDefinition(String name, Map<String, String> values) {
        super(name, JobParameterType.CLOB, values);
    }
}
