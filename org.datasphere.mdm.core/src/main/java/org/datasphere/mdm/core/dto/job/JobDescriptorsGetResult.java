
package org.datasphere.mdm.core.dto.job;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.datasphere.mdm.core.type.job.JobDescriptor;

/**
 * @author Mikhail Mikhailov on Jun 18, 2021
 */
public class JobDescriptorsGetResult {
    /**
     * The payload.
     */
    private Collection<JobDescriptor> descriptors;
    /**
     * Constructor.
     */
    public JobDescriptorsGetResult() {
        super();
    }
    /**
     * Constructor.
     */
    public JobDescriptorsGetResult(Collection<JobDescriptor> descriptors) {
        this();
        this.descriptors = descriptors;
    }
    /**
     * @return the descriptors
     */
    public Collection<JobDescriptor> getDescriptors() {
        return Objects.isNull(descriptors) ? Collections.emptyList() : descriptors;
    }
    /**
     * @param descriptors the descriptors to set
     */
    public void setDescriptors(Collection<JobDescriptor> descriptors) {
        this.descriptors = descriptors;
    }
}
