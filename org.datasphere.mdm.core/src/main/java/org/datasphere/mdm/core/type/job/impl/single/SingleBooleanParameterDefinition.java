
package org.datasphere.mdm.core.type.job.impl.single;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 * Boolean singleton.
 */
public class SingleBooleanParameterDefinition extends SingleJobParameterDefinition<Boolean> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param value
     */
    public SingleBooleanParameterDefinition(String name, Boolean value) {
        super(name, JobParameterType.BOOLEAN, value);
    }
}
