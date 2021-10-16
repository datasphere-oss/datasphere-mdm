
package org.datasphere.mdm.core.type.job.impl.single;

import java.util.Objects;

import org.datasphere.mdm.core.type.job.JobParameterDefinition;
import org.datasphere.mdm.core.type.job.JobParameterKind;
import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 20, 2021
 */
public abstract class SingleJobParameterDefinition<X> extends JobParameterDefinition<X> {
    /**
     * The value.
     */
    protected X value;
    /**
     * Constructor.
     */
    protected SingleJobParameterDefinition(String name, JobParameterType type) {
        super(name, type, JobParameterKind.SINGLE);
    }
    /**
     * Constructor.
     */
    protected SingleJobParameterDefinition(String name, JobParameterType type, X value) {
        this(name, type);
        this.value = value;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSingle() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public X single() {
        return value;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return Objects.isNull(value);
    }
}
