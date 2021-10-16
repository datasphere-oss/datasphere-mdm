
package org.datasphere.mdm.core.type.job.impl.collection;

import java.time.LocalDateTime;
import java.util.Collection;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class CollectionTimestampParameterDefinition extends CollectionJobParameterDefinition<LocalDateTime> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public CollectionTimestampParameterDefinition(String name, Collection<LocalDateTime> values) {
        super(name, JobParameterType.TIMESTAMP, values);
    }
}
