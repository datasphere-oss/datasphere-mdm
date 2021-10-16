
package org.datasphere.mdm.core.service.impl.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.quartz.CronTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

/**
 * @author Mikhail Mikhailov on May 26, 2021
 * Collects CronTrigger beans during platform startup.
 * It is expected that, those beans are {@link CronTriggerFactoryBean} created instances,
 * which have "jobDetail" param set (No job details are collected).
 */
public class CustomCronTriggerBeanPostProcessor implements BeanPostProcessor {
    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomCronTriggerBeanPostProcessor.class);
    /**
     * Triggers.
     */
    private final List<CronTrigger> collected = new ArrayList<>();
    /**
     * Constructor.
     */
    public CustomCronTriggerBeanPostProcessor() {
        super();
    }
    /**
     * @return the collected
     */
    public List<CronTrigger> getCollected() {
        return collected;
    }
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        CronTrigger trigger = null;
        if (bean instanceof CronTrigger) {
            trigger = (CronTrigger) bean;
        } else if (bean instanceof FactoryBean
            && CronTrigger.class.isAssignableFrom(((FactoryBean<?>) bean).getObjectType())) {
            try {
                trigger = ((FactoryBean<CronTrigger>) bean).getObject();
            } catch (Exception e) {
                LOGGER.warn("Can not narrow CronTrigger instance.", e);
            }
        }

        if (Objects.nonNull(trigger)) {
            collected.add(trigger);
        }

        return bean;
    }
}
