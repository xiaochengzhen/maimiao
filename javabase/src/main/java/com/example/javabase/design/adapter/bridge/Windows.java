package com.example.javabase.design.adapter.bridge;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 17:28
 */
public class Windows extends OperatingSystem{

    public Windows(Video video) {
        super(video);
    }

    @Override
    void play(String fileName) {
        video.decode(fileName);
        System.out.println("windows播放"+fileName);
    }
}
