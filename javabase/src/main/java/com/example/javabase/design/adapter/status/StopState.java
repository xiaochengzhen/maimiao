package com.example.javabase.design.adapter.status;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 15:04
 */
public class StopState extends LiftState{
    @Override
    public void open() {
        super.context.setLiftState(Context.OPEN_STATE);
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.CLOSE_STATE);
    }

    @Override
    public void run() {
        super.context.setLiftState(Context.RUN_STATE);
    }

    @Override
    public void stop() {
        System.out.println("电梯停止了");
    }
}
