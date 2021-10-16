

package org.datasphere.mdm.core.service.impl.job;

import org.quartz.CronTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.datasphere.mdm.system.service.AfterPlatformStartup;

/**
 * @author Mikhail Mikhailov
 * Thin wrapper around {@link SchedulerFactoryBean} to postpone initialization.
 */
public class CustomSchedulerFactoryBean extends SchedulerFactoryBean implements AfterPlatformStartup {
    /**
     * This logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSchedulerFactoryBean.class);
    /**
     * CronTrigger beans collector.
     */
    private CustomCronTriggerBeanPostProcessor collector;
    /**
     * Constructor.
     */
    public CustomSchedulerFactoryBean() {
        super();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPlatformStartup() {

        try {

            setTriggers(collector.getCollected().toArray(CronTrigger[]::new));
            super.afterPropertiesSet();
            start();
            collector.getCollected().clear();

        } catch (Exception e) {
            LOGGER.error("Scheduler factory afterPlatformStartup failed!", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Quartz scheduler factory started.");
    }
    /**
     * @param collector the collector to set
     */
    public void setCollector(CustomCronTriggerBeanPostProcessor collector) {
        this.collector = collector;
    }
}
