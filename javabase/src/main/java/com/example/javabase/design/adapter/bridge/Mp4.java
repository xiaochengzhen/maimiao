package com.example.javabase.design.adapter.bridge;

/**
 * @author xiaobo
 * @description
 * @date 2023/2/24 17:27
 */
public class Mp4 implements Video{
    @Override
    public void decode(String fileName) {
        System.out.println("decode mp4"+fileName);
    }
}
