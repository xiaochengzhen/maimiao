package com.example.javabase.design.adapter.bridge;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 17:24
 */
public abstract class OperatingSystem {
      Video video;

    public OperatingSystem(Video video) {
        this.video = video;
    }

    abstract void play(String fileName);
}
