
package org.datasphere.mdm.core.type.job.impl.single;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 * Double singleton.
 */
public class SingleNumberParameterDefinition extends SingleJobParameterDefinition<Double> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param value
     */
    public SingleNumberParameterDefinition(String name, Double value) {
        super(name, JobParameterType.NUMBER, value);
    }
}
