
package org.datasphere.mdm.core.type.job.impl.collection;

import java.time.LocalTime;
import java.util.Collection;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class CollectionTimeParameterDefinition extends CollectionJobParameterDefinition<LocalTime> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public CollectionTimeParameterDefinition(String name, Collection<LocalTime> values) {
        super(name, JobParameterType.TIME, values);
    }
}
