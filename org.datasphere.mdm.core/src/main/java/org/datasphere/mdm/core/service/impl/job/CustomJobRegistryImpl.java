

/**
 * Date: 16.03.2016
 */

package org.datasphere.mdm.core.service.impl.job;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.datasphere.mdm.core.service.job.CustomJobRegistry;
import org.datasphere.mdm.core.type.job.JobDescriptor;
import org.datasphere.mdm.core.type.job.JobFraction;
import org.springframework.batch.core.configuration.support.MapJobRegistry;

/**
 * @author amagdenko
 * Custom implementation of the job registry, intercepting also benas of type {@link JobDescriptor}.
 */
public class CustomJobRegistryImpl extends MapJobRegistry implements CustomJobRegistry {

    private final ConcurrentMap<String, JobDescriptor> descriptors = new ConcurrentHashMap<>();

    private final ConcurrentMap<String, List<JobFraction>> fractions = new ConcurrentHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(JobDescriptor descriptor) {

        Objects.requireNonNull(descriptor, "Job template parameters must not be null!");
        final String name = descriptor.getJobName();

        descriptors.put(name, descriptor);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JobDescriptor getDescriptor(String name){
        return descriptors.get(name);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getDescriptorNames() {
        return Collections.unmodifiableSet(descriptors.keySet());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void register(String jobName, JobFraction fraction) {

        Objects.requireNonNull(jobName, "Job name must not be null.");
        Objects.requireNonNull(fraction, "Job fraction must not be null.");

        fractions.computeIfAbsent(jobName, k -> new ArrayList<>())
                 .add(fraction);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<JobFraction> getFractions(String jobName) {
        List<JobFraction> hits = fractions.get(jobName);
        return Objects.isNull(hits) ? Collections.emptyList() : Collections.unmodifiableList(hits);
    }
}
