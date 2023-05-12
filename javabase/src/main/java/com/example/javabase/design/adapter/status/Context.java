package com.example.javabase.design.adapter.status;

/**
 * @author xiaobo
 * @description 上下文
 * @date 2023/3/14 15:05
 */
public class Context {

    private LiftState liftState;
    public static final LiftState OPEN_STATE = new OpenState();
    public static final LiftState CLOSE_STATE = new CloseState();
    public static final LiftState RUN_STATE = new RunState();
    public static final LiftState STOP_STATE = new StopState();
    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        this.liftState.setContext(this);
    }
    public void open() {
        this.liftState.open();
    }
    public void close() {
        this.liftState.close();
    }
    public void run() {
        this.liftState.run();
    }
    public void stop() {
        this.liftState.stop();
    }
    // 测试
    public static void main(String[] args){
        Context context = new Context();
        context.setLiftState(new CloseState());
        //电梯门打开了
        //电梯门关闭了!
        //电梯正在运行...
        //电梯停止了!
        context.open();
        context.close();
        context.run();
        context.stop();
    }
}
