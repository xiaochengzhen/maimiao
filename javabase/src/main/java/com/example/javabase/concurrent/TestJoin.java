package com.example.javabase.concurrent;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/4 10:14
 */
public class TestJoin {

    public static void main(String[] args) {
        Thread3 thread3 = new Thread3();
        Thread2 thread2 = new Thread2(thread3);
        Thread1 thread1 = new Thread1(thread2);
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static  class Thread1 extends  Thread{
        private Thread2 thread2;
        public Thread1(Thread2 thread2) {
            this.thread2 = thread2;
        }
        @Override
        public void run() {
            try {
                thread2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1");

        }
    }

    public static  class Thread2 extends  Thread{
        private Thread3 thread3;
        public Thread2(Thread3 thread3) {
            this.thread3 = thread3;
        }
        @Override
        public void run() {
            try {
                thread3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2");
        }
    }

    public static  class Thread3 extends  Thread{
        @Override
        public void run() {
            System.out.println("thread3");
        }
    }
}
