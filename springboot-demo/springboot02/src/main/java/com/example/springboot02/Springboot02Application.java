package com.example.springboot02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* 配置文件的加载顺序，同一个文件夹中，先加载 yml-》yaml-》properties， 如果多个配置文件会互补，前面的会覆盖
* 还可以加载config/*, 意思就是可以加载文件夹下多了配置文件，形成互补
*
* 优先级：1、命令行 2、jar包外面，config下， 3、jar包外面，同一级 4、config下  5、根目录下
* java -jar .\springboot02-0.0.1-SNAPSHOT.jar --server.port=8085
* java -jar .\springboot02-0.0.1-SNAPSHOT.jar --spring.config.location=D:\config/
*
* */

@SpringBootApplication
public class Springboot02Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02Application.class, args);
    }

}
