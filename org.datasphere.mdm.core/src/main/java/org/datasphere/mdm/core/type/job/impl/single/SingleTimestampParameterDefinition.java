
package org.datasphere.mdm.core.type.job.impl.single;

import java.time.LocalDateTime;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 * LDT singleton.
 */
public class SingleTimestampParameterDefinition extends SingleJobParameterDefinition<LocalDateTime> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param value
     */
    public SingleTimestampParameterDefinition(String name, LocalDateTime value) {
        super(name, JobParameterType.TIMESTAMP, value);
    }
}
