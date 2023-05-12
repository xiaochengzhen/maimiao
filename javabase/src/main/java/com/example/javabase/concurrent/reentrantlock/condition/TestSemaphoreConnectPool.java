package com.example.javabase.concurrent.reentrantlock.condition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/7 10:39
 */
public class TestSemaphoreConnectPool {

    public static void main(String[] args) {
        ConnectPool connectPool = new ConnectPool(5);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            executorService.submit(()->{
                Connect connect = null;
                try {
                    connect = connectPool.openConnect();
                    System.out.println(Thread.currentThread().getName()+"获取连接");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    connectPool.releaseConnect(connect);
                    System.out.println(Thread.currentThread().getName()+"释放连接");
                }
            });
        }
    }


    static class ConnectPool{
        private int size;
        private Connect[] connects;
        private boolean[] connectFlag;
        private Semaphore semaphore;

        public ConnectPool(int size) {
            this.size = size;
            semaphore = new Semaphore(size, true);
            connects = new Connect[size];
            connectFlag = new boolean[size];
            initConnect();
        }

        public void initConnect() {
            for (int i = 0; i < size; i++) {
                 connects[i] = new Connect();
            }
        }
        public Connect openConnect() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getConnect();
        }

        public synchronized Connect getConnect() {
            for (int i = 0; i <size; i++) {
                boolean b = connectFlag[i];
                if (!b) {
                    connectFlag[i] = true;
                    return connects[i];
                }
            }
            return null;
        }

        public synchronized void releaseConnect(Connect connect) {
            for (int i = 0; i < size; i++) {
                if (connect == connects[i]) {
                    connectFlag[i] = false;
                    semaphore.release();
                }
            }
        }


    }





    static class Connect {
        private static int count = 1;
        private int id  = count++;
        public  Connect() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("id="+id+"已经连接数据库");
        }
    }
}
