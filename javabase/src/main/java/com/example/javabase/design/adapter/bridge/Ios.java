package com.example.javabase.design.adapter.bridge;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 17:29
 */
public class Ios extends OperatingSystem{

    public Ios(Video video) {
        super(video);
    }

    @Override
    void play(String fileName) {
        video.decode(fileName);
        System.out.println("ios播放"+fileName);
    }
}
