

package org.datasphere.mdm.core.type.job.impl.collection;

import java.time.LocalDate;
import java.util.Collection;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class CollectionDateParameterDefinition extends CollectionJobParameterDefinition<LocalDate> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public CollectionDateParameterDefinition(String name, Collection<LocalDate> values) {
        super(name, JobParameterType.DATE, values);
    }
}
