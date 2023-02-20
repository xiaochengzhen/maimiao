package com.example.schedule1.task;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/4 09:48
 */
@Data
@Slf4j
@Component
@PropertySource("classpath:/task-config.ini")
public class ScheduleTask implements SchedulingConfigurer {
    @Value("${printTime.cron}")
    private String cron;

    @Value("${printTime.timer}")
    private Long timer;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {
            log.info("Current time： {}", LocalDateTime.now());
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                // 使用CronTrigger触发器，可动态修改cron表达式来操作循环规则
                CronTrigger cronTrigger = new CronTrigger(cron);
                Date nextExecutionTime = cronTrigger.nextExecutionTime(triggerContext);

                // 使用不同的触发器，为设置循环时间的关键，区别于CronTrigger触发器，该触发器可随意设置循环间隔时间，单位为毫秒
             /*   PeriodicTrigger periodicTrigger = new PeriodicTrigger(timer);
                Date nextExecutionTime = periodicTrigger.nextExecutionTime(triggerContext);*/
                return nextExecutionTime;
            }
        });
    }
}
