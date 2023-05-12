package com.example.javabase.design.adapter.status;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 15:04
 */
public class RunState extends LiftState{
    @Override
    public void open() {
    }

    @Override
    public void close() {
    }

    @Override
    public void run() {
        System.out.println("电梯运行了");
    }

    @Override
    public void stop() {
        super.context.setLiftState(Context.STOP_STATE);
        super.context.stop();
    }
}
