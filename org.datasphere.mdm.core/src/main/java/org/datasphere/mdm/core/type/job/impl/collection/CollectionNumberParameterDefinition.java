
package org.datasphere.mdm.core.type.job.impl.collection;

import java.util.Collection;

import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 23, 2021
 */
public class CollectionNumberParameterDefinition extends CollectionJobParameterDefinition<Double> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param values
     */
    public CollectionNumberParameterDefinition(String name, Collection<Double> values) {
        super(name, JobParameterType.NUMBER, values);
    }
}
