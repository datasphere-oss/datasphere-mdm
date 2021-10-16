
package org.datasphere.mdm.core.type.job.impl.single;

import java.time.LocalTime;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 * Double singleton.
 */
public class SingleTimeParameterDefinition extends SingleJobParameterDefinition<LocalTime> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param value
     */
    public SingleTimeParameterDefinition(String name, LocalTime value) {
        super(name, JobParameterType.TIME, value);
    }
}
