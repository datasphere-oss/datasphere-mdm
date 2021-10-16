
package org.datasphere.mdm.core.type.job.impl.collection;

import java.time.Instant;
import java.util.Collection;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class CollectionInstantParameterDefinition extends CollectionJobParameterDefinition<Instant> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public CollectionInstantParameterDefinition(String name, Collection<Instant> values) {
        super(name, JobParameterType.INSTANT, values);
    }
}
