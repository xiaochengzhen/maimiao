package com.example.schedule2.controller;

import com.example.schedule2.model.ScheduledFutureHolder;
import com.example.schedule2.task.HelloTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/4 10:42
 */
@RestController
@RequestMapping("/test")
public class TaskController {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    //存储任务执行的包装类
    private HashMap<String, ScheduledFutureHolder> scheduleMap = new HashMap<>();

    /**
     *启动任务
     * 如果不想手动触发任务可以使用 @PostConstruct注解来启动
     */
    @RequestMapping("/start")
    public void startTask()  {
        try {
            //初始化一个任务（这里可以初始话多个）
            HelloTask helloTask = new HelloTask();
            String corn = "0/2 * * * *  ?";
            //将任务交给任务调度器执行
            ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(helloTask, new CronTrigger(corn));

            //将任务包装成ScheduledFutureHolder
            ScheduledFutureHolder scheduledFutureHolder = new ScheduledFutureHolder();
            scheduledFutureHolder.setScheduledFuture(schedule);
            scheduledFutureHolder.setRunnableClass(helloTask.getClass());
            scheduledFutureHolder.setCorn(corn);

            scheduleMap.put(scheduledFutureHolder.getRunnableClass().getName(),scheduledFutureHolder);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 查询所有的任务
     */
    @RequestMapping("/queryTask")
    public void queryTask(){
        scheduleMap.forEach((k,v)->{
            System.out.println(k+"  "+v);
        });
    }

    /**
     * 停止任务
     * @param className
     */
    @RequestMapping("/stop/{className}")
    public void stopTask(@PathVariable  String className){
        if(scheduleMap.containsKey(className)){//如果包含这个任务
            ScheduledFuture<?> scheduledFuture = scheduleMap.get(className).getScheduledFuture();
            if(scheduledFuture!=null){
                scheduledFuture.cancel(true);
            }
        }
    }


    /**
     * 重启任务，修改任务的触发时间
     * @param className
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @RequestMapping("/restart/{className}")
    public void restartTask(@PathVariable String className) throws InstantiationException, IllegalAccessException {
        if(scheduleMap.containsKey(className)){//如果包含这个任务


            //这里的corn可以通过前端动态传过来
            String corn = "0/50 * * * *  ?";
            ScheduledFutureHolder scheduledFutureHolder = scheduleMap.get(className);
            ScheduledFuture<?> scheduledFuture = scheduledFutureHolder.getScheduledFuture();
            if(scheduledFuture!=null){
                //先停掉任务
                scheduledFuture.cancel(true);

                //修改触发时间重新启动任务
                Runnable runnable = scheduledFutureHolder.getRunnableClass().newInstance();

                ScheduledFuture<?> schedule = threadPoolTaskScheduler.schedule(runnable, new CronTrigger(corn));

                scheduledFutureHolder.setScheduledFuture(schedule);
                scheduledFutureHolder.setCorn(corn);

                scheduleMap.put(scheduledFutureHolder.getRunnableClass().getName(),scheduledFutureHolder);
            }
        }
    }
}
