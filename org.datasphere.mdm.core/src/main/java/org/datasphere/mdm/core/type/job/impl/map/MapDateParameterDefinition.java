
package org.datasphere.mdm.core.type.job.impl.map;

import java.time.LocalDate;
import java.util.Map;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class MapDateParameterDefinition extends MapJobParameterDefinition<LocalDate> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public MapDateParameterDefinition(String name, Map<String, LocalDate> values) {
        super(name, JobParameterType.DATE, values);
    }
}
