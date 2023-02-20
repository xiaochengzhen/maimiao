package com.example.schedule1.controller;

import com.example.schedule1.task.ScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/4 09:54
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TaskController {

    @Autowired
    private ScheduleTask scheduleTask;
/*

    @Autowired
    public TaskController(ScheduleTask scheduleTask) {
        this.scheduleTask = scheduleTask;
    }
*/

    @GetMapping("/updateCron")
    public String updateCron(String cron) {
        log.info("new cron :{}", cron);
        scheduleTask.setCron(cron);
        return "ok";
    }

    @GetMapping("/updateTimer")
    public String updateTimer(Long timer) {
        log.info("new timer :{}", timer);
        scheduleTask.setTimer(timer);
        return "ok";
    }

}
