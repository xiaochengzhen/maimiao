package com.example.javabase.concurrent.reentrantlock;

import java.sql.DataTruncation;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/7 09:40
 */
public class TestQeueu {

    public static void main(String[] args) {
        Queue queue = new Queue(5);
        new Thread(new Product(queue)).start();
        new Thread(new consumer(queue)).start();
    }

    static class Queue {
        private Object[] items;
        private int takeIndex;
        private int putIndex;
        private int size;
        private Lock lock;
        private Condition notEmpty;
        private Condition notFull;//队列满了

        public Queue(int capacity) {
            this.items = new Object[capacity];
            this.lock = new ReentrantLock();
            this.notEmpty = lock.newCondition();
            this.notFull = lock.newCondition();
        }

        public Object take() {
            Object object = null;
            lock.lock();
            try {
                if (size == 0) {
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object = items[takeIndex];
                size--;
                takeIndex++;
                if (takeIndex == items.length) {
                    takeIndex = 0;
                }
                items[takeIndex] = null;
                notFull.signalAll();
            } finally {
                lock.unlock();
            }
            return object;
        }

        public void put(Object value) {
            lock.lock();
            try {
                if (size == items.length) {
                    try {
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                items[putIndex] = value;
                putIndex++;
                if (putIndex == items.length) {
                    putIndex = 0;
                }

                size++;
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    static class Product implements Runnable {
        private Queue queue;

        public Product(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.put(new Random().nextInt(10));
                System.out.println("pruducter===============================");
            }
        }
    }

    static class consumer implements Runnable {
        private Queue queue;

        public consumer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object take = queue.take();
                System.out.println(take);
                System.out.println("consumer===============================");
            }
        }
    }

}
