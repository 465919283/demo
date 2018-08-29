package com.primer.demo.task;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Component
public class ScheduleListener implements BeanPostProcessor {

    @Autowired
    private Scheduler scheduler;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ScheduledJob scheduledJob = AnnotationUtils.findAnnotation(bean.getClass(), ScheduledJob.class);
        if (scheduledJob != null && bean instanceof Job) {
            JobKey jobKey = new JobKey(scheduledJob.name(), scheduledJob.group());

            JobDetail jobDetail = JobBuilder.newJob(((Job) bean).getClass())
                    .withIdentity(jobKey)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(scheduledJob.name() + "Trigger", scheduledJob.group())
                    .forJob(jobDetail)
                    .withSchedule(CronScheduleBuilder.cronSchedule(scheduledJob.cronExp()))
                    .build();

            try {
                if (!scheduler.checkExists(jobKey)) {
                    scheduler.scheduleJob(jobDetail, trigger);
                }
            } catch (SchedulerException e) {
            	e.printStackTrace();
            }
        }
        return bean;
    }
}
