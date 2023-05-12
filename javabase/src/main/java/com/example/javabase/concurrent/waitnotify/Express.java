package com.example.javabase.concurrent.waitnotify;

/**
 * @author xiaobo
 * @description
 * @date 2023/4/6 10:36
 */
public class Express {

    public final int total = 500;
    public final String ADDRESS = "shanghai";

    private int km;
    private String site;

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    public void change() {
        this.km = this.km+100;
        System.out.println("=======走了====="+this.km);
    }

    public synchronized void waitKm() {
        while (this.km < total) {
            try {
                wait();
                System.out.println("waitKm==");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.site = "shanghai";
        System.out.println("==============km:"+this.km+"=======================");
    }

    public synchronized void waitSite() {
        while (!this.site.equals("shanghai")) {
            try {
                wait();
                System.out.println("waitSite==");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("==================快递到达了===================");
    }
}
