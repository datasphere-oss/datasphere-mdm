
package org.datasphere.mdm.core.type.job.impl.single;

import org.apache.commons.lang3.StringUtils;
import org.datasphere.mdm.core.type.job.JobParameterType;

/**
 * @author Mikhail Mikhailov on Jun 21, 2021
 * String singleton.
 */
public class SingleStringParameterDefinition extends SingleJobParameterDefinition<String> {
    /**
     * Constructor.
     * @param name
     * @param type
     * @param value
     */
    public SingleStringParameterDefinition(String name, String value) {
        super(name, JobParameterType.STRING, value);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return StringUtils.isBlank(single());
    }
}
