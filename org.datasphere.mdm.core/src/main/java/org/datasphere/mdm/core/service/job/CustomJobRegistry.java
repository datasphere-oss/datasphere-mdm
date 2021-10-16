

/**
 * Date: 16.03.2016
 */

package org.datasphere.mdm.core.service.job;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.datasphere.mdm.core.type.job.JobDescriptor;
import org.datasphere.mdm.core.type.job.JobFraction;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobRegistry;

/**
 * @author amagdenko
 * This extends String's own JobRegistry to support job descriptor beans.
 */
public interface CustomJobRegistry extends JobRegistry {
    /**
     * Adds a new job descriptor to the registery.
     * @param descriptor the descriptor to add
     * @throws DuplicateJobException if a descriptor with the same job name is alredy added
     */
    void register(@Nonnull JobDescriptor descriptor);
    /**
     * Gets descriptor for the given job name.
     * @param jobName the job name
     * @return descriptor or null, if the name is unknown
     */
    @Nullable
    JobDescriptor getDescriptor(String jobName);
    /**
     * Gets the names of jobs currently registered.
     * @return names collection
     */
    @Nonnull
    Set<String> getDescriptorNames();
    /**
     * Registers modular job fraction for the given job name.
     * @param jobName the name of the job.
     * @param fraction the fraction to register
     */
    void register(@Nonnull String jobName, @Nonnull JobFraction fraction);
    /**
     * Gets fractions, that may have been registered for the given job name.
     * @param jobName the job name
     * @return collection of fractions
     */
    @Nonnull
    Collection<JobFraction> getFractions(String jobName);
}
