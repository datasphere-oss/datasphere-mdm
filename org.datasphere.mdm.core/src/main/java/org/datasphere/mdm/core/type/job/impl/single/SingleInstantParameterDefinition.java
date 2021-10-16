
package org.datasphere.mdm.core.type.job.impl.single;

import java.time.Instant;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 * Double singleton.
 */
public class SingleInstantParameterDefinition extends SingleJobParameterDefinition<Instant> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param value
     */
    public SingleInstantParameterDefinition(String name, Instant value) {
        super(name, JobParameterType.INSTANT, value);
    }
}
