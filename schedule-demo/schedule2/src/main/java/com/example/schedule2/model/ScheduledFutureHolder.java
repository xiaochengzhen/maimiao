package com.example.schedule2.model;

import lombok.Data;

import java.util.concurrent.ScheduledFuture;

/**
 * @author xiaobo
 * @description
 * @date 2022/6/4 10:45
 */
@Data
public class ScheduledFutureHolder {

    private ScheduledFuture<?> scheduledFuture;

    private Class<? extends Runnable> runnableClass;

    private String corn;

}
