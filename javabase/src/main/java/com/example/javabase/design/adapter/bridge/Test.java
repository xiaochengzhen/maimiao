package com.example.javabase.design.adapter.bridge;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 17:26
 */
public class Test {

    public static void main(String[] args) {
        Video mp4 = new Mp4();
        OperatingSystem operatingSystem = new Windows(mp4);
        operatingSystem.play("电影");

        Video avi = new Avi();
        OperatingSystem operatingSystem1 = new Ios(avi);
        operatingSystem1.play("电影");

    }
}
