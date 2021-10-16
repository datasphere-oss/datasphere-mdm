
package org.datasphere.mdm.core.service.impl.job;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.datasphere.mdm.core.context.JobDescriptorsGetContext;
import org.datasphere.mdm.core.dto.job.JobDescriptorsGetResult;
import org.datasphere.mdm.core.service.job.CustomJobRegistry;
import org.datasphere.mdm.core.type.job.JobDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mikhail Mikhailov on Jun 18, 2021
 * Job descriptors management.
 */
@Component
public class JobDescriptorsComponent {
    /**
     * The registry.
     */
    @Autowired
    private CustomJobRegistry jobRegistry;
    /**
     * Constructor.
     */
    public JobDescriptorsComponent() {
        super();
    }
    /**
     * Gets descriptors using supplied criteria.
     * @param ctx the context
     * @return result
     */
    public JobDescriptorsGetResult descriptors(JobDescriptorsGetContext ctx) {

        Collection<JobDescriptor> descriptors = Collections.emptyList();
        if (ctx.isAll()) {
            descriptors = jobRegistry.getDescriptorNames().stream()
                    .map(jobRegistry::getDescriptor)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } else if (CollectionUtils.isNotEmpty(ctx.getNames())) {
            descriptors = ctx.getNames().stream()
                    .filter(Objects::nonNull)
                    .map(jobRegistry::getDescriptor)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }

        return new JobDescriptorsGetResult(descriptors);
    }
}
