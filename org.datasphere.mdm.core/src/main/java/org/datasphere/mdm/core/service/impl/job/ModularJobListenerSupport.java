
package org.datasphere.mdm.core.service.impl.job;

import java.util.Collection;
import java.util.Objects;

import org.datasphere.mdm.core.exception.CoreExceptionIds;
import org.datasphere.mdm.core.exception.JobException;
import org.datasphere.mdm.core.type.job.JobDescriptor;
import org.datasphere.mdm.core.type.job.JobFraction;
import org.datasphere.mdm.core.util.JobUtils;

/**
 * @author Mikhail Mikhailov on Jul 12, 2021
 */
public abstract class ModularJobListenerSupport {
    /**
     * Job name. Null will cause havoc.
     */
    private final String jobName;
    /**
     * Constructor.
     * @param jobName the job name
     */
    protected ModularJobListenerSupport(String jobName) {
        super();
        this.jobName = jobName;
    }
    /**
     * Gets fractions.
     * @return fractions
     */
    public Collection<JobFraction> getFractions() {

        JobDescriptor descriptor = JobUtils.getDescriptor(jobName);
        if (Objects.isNull(descriptor)) {
            throw new JobException("Decsriptor for job [{}] not found at runtime.",
                    CoreExceptionIds.EX_JOB_DESCRIPTOR_NOT_FOUND_AT_RUNTIME, jobName);
        }

        return descriptor.getFractions();
    }
    /**
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }
}
