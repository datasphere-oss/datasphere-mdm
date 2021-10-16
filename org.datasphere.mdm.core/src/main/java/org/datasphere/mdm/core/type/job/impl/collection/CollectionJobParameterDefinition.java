
package org.datasphere.mdm.core.type.job.impl.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.type.job.JobParameterDefinition;
import org.datasphere.mdm.core.type.job.JobParameterKind;
import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 */
public abstract class CollectionJobParameterDefinition<X> extends JobParameterDefinition<X> {
    /**
     * The values.
     */
    protected Collection<X> values;
    /**
     * Constructor.
     * @param name
     * @param type
     */
    protected CollectionJobParameterDefinition(String name, JobParameterType type) {
        super(name, type, JobParameterKind.COLLECTION);
    }
    /**
     * Constructor.
     * @param name
     * @param type
     */
    protected CollectionJobParameterDefinition(String name, JobParameterType type, Collection<X> values) {
        this(name, type);
        this.values = values;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCollection() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<X> collection() {
        return Objects.isNull(values) ? Collections.emptyList() : values;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(values);
    }
}
