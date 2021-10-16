
package org.datasphere.mdm.core.type.job.impl.single;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 * Potentially large string singleton.
 */
public class SingleClobParameterDefinition extends SingleJobParameterDefinition<String> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param value
     */
    public SingleClobParameterDefinition(String name, String value) {
        super(name, JobParameterType.CLOB, value);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return StringUtils.isBlank(single());
    }
}
