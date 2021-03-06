
package org.datasphere.mdm.core.service.impl.job;

import java.util.Objects;

import org.datasphere.mdm.core.context.JobDefinitionSchedulingContext;
import org.datasphere.mdm.core.service.job.JobCommonParameters;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author Mikhail Mikhailov on Jul 5, 2021
 */
@Component
public class JobSchedulingComponent {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JobSchedulingComponent.class);
    /**
     * Local quartz group.
     */
    private static final String QUARTZ_GROUP = "quartz-batch";
    /**
     * Quartz scheduler (cannot be accessed directly).
     */
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    /**
     * Constructor.
     */
    public JobSchedulingComponent() {
        super();
    }
    /**
     * Schedule unidata job.
     *
     * @param jobId Unidata job id.
     * @param cronExpression Cron expression.
     */
    public void schedule(JobDefinitionSchedulingContext ctx) {

        try {

            Objects.requireNonNull(ctx.getJobDefinitionId(), "Job definition id must not be null.");

            JobBuilder jobBuilder = JobBuilder
                    .newJob(JobLauncherDetail.class)
                    .withIdentity(jobKey(ctx.getJobDefinitionId()))
                    .usingJobData("jobId", String.valueOf(ctx.getJobDefinitionId()));

            if (Objects.nonNull(ctx.getParentJobExecutionId())) {
                jobBuilder.usingJobData(JobCommonParameters.PARAM_PARENT_JOB_EXECUTION_ID, String.valueOf(ctx.getParentJobExecutionId()));
            }

            JobDetail jobDetail = jobBuilder.build();
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey(ctx.getJobDefinitionId()))
                    .withSchedule(CronScheduleBuilder.cronSchedule(ctx.getCronExpression()))
                    .build();

            schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);

            LOGGER.info("Schedule job in quartz [jobId={}, triggerKey={}]", ctx.getJobDefinitionId(), trigger.getKey());
        } catch (SchedulerException e) {
            LOGGER.error("Failed to schedule quartz job: [{}]", ctx.getJobDefinitionId(), e);
        }
    }
    /**
     * @param jobId
     */
    public void unschedule(JobDefinitionSchedulingContext ctx) {

        try {

            Objects.requireNonNull(ctx.getJobDefinitionId(), "Job definition id must not be null.");

            TriggerKey key = triggerKey(ctx.getJobDefinitionId());
            if (schedulerFactoryBean.getScheduler().checkExists(key)) {
                schedulerFactoryBean.getScheduler().unscheduleJob(key);
                LOGGER.info("Unschedule job in quartz [jobId={}, triggerKey={}]", ctx.getJobDefinitionId(), key);
            }
        } catch (SchedulerException e) {
            LOGGER.error("Failed to unschedule job: " + ctx.getJobDefinitionId(), e);
        }
    }

    private JobKey jobKey(Long jobId) {
        return JobKey.jobKey("job-" + jobId, QUARTZ_GROUP);
    }

    private TriggerKey triggerKey(Long jobId) {
        return TriggerKey.triggerKey("trigger-" + jobId, QUARTZ_GROUP);
    }
}
