package com.example.javabase.design.adapter.status;

import lombok.Data;

/**
 * @author xiaobo
 * @description 电梯状态
 * @date 2023/3/14 15:03
 */

@Data
public abstract class LiftState {
    protected Context context;
    public abstract void open();
    public abstract void close();
    public abstract void run();
    public abstract void stop();
}
