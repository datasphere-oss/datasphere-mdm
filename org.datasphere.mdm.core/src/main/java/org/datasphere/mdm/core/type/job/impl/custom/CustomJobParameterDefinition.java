
package org.datasphere.mdm.core.type.job.impl.custom;

import org.datasphere.mdm.core.type.job.JobParameterDefinition;
import org.datasphere.mdm.core.type.job.JobParameterKind;
import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 20, 2021
 */
public abstract class CustomJobParameterDefinition<X> extends JobParameterDefinition<X> {
    /**
     * The value.
     */
    protected X value;
    /**
     * Constructor.
     */
    protected CustomJobParameterDefinition(String name, X value) {
        super(name, JobParameterType.CUSTOM, JobParameterKind.CUSTOM);
        this.value = value;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCustom() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public X asCustom() {
        return value;
    }
}
