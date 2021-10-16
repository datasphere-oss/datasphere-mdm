

package org.datasphere.mdm.core.type.job.impl.collection;

import java.util.Collection;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class CollectionBooleanParameterDefinition extends CollectionJobParameterDefinition<Boolean> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public CollectionBooleanParameterDefinition(String name, Collection<Boolean> values) {
        super(name, JobParameterType.BOOLEAN, values);
    }
}
