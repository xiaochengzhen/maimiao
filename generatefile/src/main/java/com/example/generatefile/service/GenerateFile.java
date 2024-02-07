package com.example.generatefile.service;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @description 
 * @author xiaobo
 * @date 2024/2/2 8:04
 */
public class GenerateFile {
    public static void main(String[] args) {
        try (FileWriter writer = new FileWriter("test.txt")) {
            writer.write("123");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件生成失败！");
        }
    }
}
