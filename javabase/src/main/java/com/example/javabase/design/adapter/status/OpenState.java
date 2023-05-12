package com.example.javabase.design.adapter.status;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/14 15:04
 */
public class OpenState extends LiftState{
    @Override
    public void open() {
        System.out.println("电梯门打开了");
    }

    @Override
    public void close() {
        super.context.setLiftState(Context.CLOSE_STATE);
        super.context.close();
    }

    @Override
    public void run() {
    }

    @Override
    public void stop() {
    }
}
