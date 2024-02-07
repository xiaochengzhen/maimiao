package com.example.generatefile.service;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 获取用户输入的信息
        System.out.print("请输入要写入文件的内容: ");
        String content = scanner.nextLine();

        System.out.print("请输入文件名（包括扩展名，例如example.txt）: ");
        String fileName = scanner.nextLine();

        generateFile(content, fileName);

        System.out.println("文件生成成功！");
    }

    private static void generateFile(String content, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件生成失败！");
        }
    }
}
