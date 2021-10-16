
package org.datasphere.mdm.core.type.job.impl.single;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 * Long singleton.
 */
public class SingleIntegerParameterDefinition extends SingleJobParameterDefinition<Long> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param value
     */
    public SingleIntegerParameterDefinition(String name, Long value) {
        super(name, JobParameterType.INTEGER, value);
    }
}
