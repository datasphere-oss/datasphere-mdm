
package org.datasphere.mdm.core.type.job.impl.single;

import java.time.LocalDate;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 * LocalDate singleton.
 */
public class SingleDateParameterDefinition extends SingleJobParameterDefinition<LocalDate> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param value
     */
    public SingleDateParameterDefinition(String name, LocalDate value) {
        super(name, JobParameterType.DATE, value);
    }
}
