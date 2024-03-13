package com.example.reptile.webmagic;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/28 13:51
 */
public class MyScheduler implements Scheduler {
    @Override
    public void push(Request request, Task task) {

    }

    @Override
    public Request poll(Task task) {
        return null;
    }
}
