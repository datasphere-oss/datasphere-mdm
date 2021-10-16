
package org.datasphere.mdm.core.type.job.impl.map;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.MapUtils;
import org.datasphere.mdm.core.type.job.JobParameterDefinition;
import org.datasphere.mdm.core.type.job.JobParameterKind;
import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 */
public abstract class MapJobParameterDefinition<X> extends JobParameterDefinition<X> {
    /**
     * The values.
     */
    protected Map<String, X> values;
    /**
     * Constructor.
     * @param name
     * @param type
     */
    protected MapJobParameterDefinition(String name, JobParameterType type) {
        super(name, type, JobParameterKind.MAP);
    }
    /**
     * Constructor.
     * @param name
     * @param type
     */
    protected MapJobParameterDefinition(String name, JobParameterType type, Map<String, X> values) {
        this(name, type);
        this.values = values;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMap() {
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, X> map() {
        return Objects.isNull(values) ? Collections.emptyMap() : values;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return MapUtils.isEmpty(values);
    }
}
