package com.example.javabase.concurrent.waitnotify;

import java.util.concurrent.TimeUnit;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/6 10:46
 */
public class TestExpress {
    public static void main(String[] args) throws InterruptedException {
        Express express = new Express(0, "beijing");
        KmThread kmThread = new KmThread(express);
        SiteThread siteThread = new SiteThread(express);
        kmThread.start();
        siteThread.start();
        for (int i = 0; i <5; i++) {
            TimeUnit.SECONDS.sleep(1);
            synchronized (express) {
                express.change();
                express.notifyAll();
            }
        }
    }

    public static  class KmThread extends Thread {
        private Express express;

        public KmThread(Express express) {
            this.express = express;
        }
        @Override
        public  void run() {
            express.waitKm();
        }
    }

    public static class SiteThread extends Thread {
        private Express express;

        public SiteThread(Express express) {
            this.express = express;
        }
        @Override
        public void run() {
            express.waitSite();
        }
    }
}
