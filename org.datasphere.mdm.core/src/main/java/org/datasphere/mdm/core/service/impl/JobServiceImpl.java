

package org.datasphere.mdm.core.service.impl;

import java.util.List;

import org.datasphere.mdm.core.context.JobDefinitionSchedulingContext;
import org.datasphere.mdm.core.context.JobDefinitionUpsertContext;
import org.datasphere.mdm.core.context.JobDefinitionsEnableContext;
import org.datasphere.mdm.core.context.JobDefinitionsMarkContext;
import org.datasphere.mdm.core.context.JobDefinitionsQueryContext;
import org.datasphere.mdm.core.context.JobDefinitionsRemoveContext;
import org.datasphere.mdm.core.context.JobDescriptorsGetContext;
import org.datasphere.mdm.core.context.JobExecutionQueryContext;
import org.datasphere.mdm.core.context.JobExecutionStartContext;
import org.datasphere.mdm.core.context.JobExecutionStatusContext;
import org.datasphere.mdm.core.context.JobExecutionStopContext;
import org.datasphere.mdm.core.context.StepExecutionQueryContext;
import org.datasphere.mdm.core.dto.job.JobDefinitionUpsertResult;
import org.datasphere.mdm.core.dto.job.JobDefinitionsQueryResult;
import org.datasphere.mdm.core.dto.job.JobDescriptorsGetResult;
import org.datasphere.mdm.core.dto.job.JobExecutionStartResult;
import org.datasphere.mdm.core.dto.job.JobExecutionStatusResult;
import org.datasphere.mdm.core.dto.job.JobExecutionsQueryResult;
import org.datasphere.mdm.core.dto.job.StepExecutionQueryResult;
import org.datasphere.mdm.core.service.JobService;
import org.datasphere.mdm.core.service.impl.job.JobDefinitionsComponent;
import org.datasphere.mdm.core.service.impl.job.JobDescriptorsComponent;
import org.datasphere.mdm.core.service.impl.job.JobExecutionsComponent;
import org.datasphere.mdm.core.service.impl.job.JobSchedulingComponent;
import org.datasphere.mdm.core.service.job.JobComplexParameterHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.datasphere.mdm.system.util.IdUtils;

/**
 * Job service to manipulate all jobs deployed in system.
 *
 * @author Alexander Magdenko
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobComplexParameterHolder complexJobParameterHolder;

    @Autowired
    private JobDescriptorsComponent jobDescriptorsComponent;

    @Autowired
    private JobDefinitionsComponent jobDefinitionsComponent;

    @Autowired
    private JobSchedulingComponent jobSchedulingComponent;

    @Autowired
    private JobExecutionsComponent jobExecutionsComponent;
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // NEW
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * {@inheritDoc}
     */
    @Override
    public JobDescriptorsGetResult descriptors(JobDescriptorsGetContext ctx) {
        return jobDescriptorsComponent.descriptors(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JobDefinitionsQueryResult definitions(JobDefinitionsQueryContext ctx) {
        return jobDefinitionsComponent.get(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public JobDefinitionUpsertResult upsert(JobDefinitionUpsertContext ctx) {
        return jobDefinitionsComponent.upsert(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void enable(JobDefinitionsEnableContext ctx) {
        jobDefinitionsComponent.enable(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void mark(JobDefinitionsMarkContext ctx) {
        jobDefinitionsComponent.mark(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void remove(JobDefinitionsRemoveContext ctx) {
        jobDefinitionsComponent.remove(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> tags() {
        return jobDefinitionsComponent.tags();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecutionsQueryResult executions(JobExecutionQueryContext ctx) {
        return jobExecutionsComponent.executions(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void schedule(JobDefinitionSchedulingContext ctx) {
        jobSchedulingComponent.schedule(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void unschedule(JobDefinitionSchedulingContext ctx) {
        jobSchedulingComponent.unschedule(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecutionStartResult start(JobExecutionStartContext ctx) {
        return jobExecutionsComponent.start(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void stop(JobExecutionStopContext ctx) {
        jobExecutionsComponent.stop(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public StepExecutionQueryResult steps(StepExecutionQueryContext ctx) {
        return jobExecutionsComponent.steps(ctx);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JobExecutionStatusResult status(JobExecutionStatusContext ctx) {
        return jobExecutionsComponent.status(ctx);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // End of NEW
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param complexParameter - any object which you want to pass to job
     * @return key in storage which help find complex parameter in partitioner
     */
    @Override
    public String putComplexParameter(Object complexParameter) {
        String storageKey = IdUtils.v4String();
        complexJobParameterHolder.putComplexParameter(storageKey, complexParameter);
        return storageKey;
    }
}
