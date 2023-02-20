package com.example.schedule2.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/4 10:39
 */
@Component
public class TestTask {

    @Scheduled(cron = "0/5 * * * *  ?")
    public void sayHello() {
        System.out.println("hello2222 ...");

        //用于计算下次任务的执行时间
        CronTrigger cronTrigger = new CronTrigger("0/5 * * * *  ?");
        Date nextFireTime = cronTrigger.nextExecutionTime(new SimpleTriggerContext());
        System.out.println("任务下次的执行时间>>>"+nextFireTime);
    }
}
