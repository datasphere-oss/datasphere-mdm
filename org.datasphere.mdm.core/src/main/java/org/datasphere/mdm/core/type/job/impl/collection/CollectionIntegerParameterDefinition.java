
package org.datasphere.mdm.core.type.job.impl.collection;

import java.util.Collection;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class CollectionIntegerParameterDefinition extends CollectionJobParameterDefinition<Long> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public CollectionIntegerParameterDefinition(String name, Collection<Long> values) {
        super(name, JobParameterType.INTEGER, values);
    }
}
