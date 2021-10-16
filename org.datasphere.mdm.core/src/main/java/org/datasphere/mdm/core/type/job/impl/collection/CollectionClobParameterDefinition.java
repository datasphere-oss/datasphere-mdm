

package org.datasphere.mdm.core.type.job.impl.collection;

import java.util.Collection;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class CollectionClobParameterDefinition extends CollectionJobParameterDefinition<String> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public CollectionClobParameterDefinition(String name, Collection<String> values) {
        super(name, JobParameterType.CLOB, values);
    }
}
