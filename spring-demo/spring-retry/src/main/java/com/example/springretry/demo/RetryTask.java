package com.example.springretry.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.remoting.RemoteAccessException;

import java.util.Random;

/**
 * @author xiaobo
 * @description
 * @date 2022/8/26 14:27
 */
@Slf4j
public class RetryTask {

    public static boolean retryTask(String param)  {
        log.info("收到请求参数:{}",param);
        Random random = new Random();
        int i = random.nextInt(10);
        log.info("随机生成的数:{}",i);
        if (i == 0) {
            log.info("为0,抛出参数异常.");
            throw new IllegalArgumentException("参数异常");
        }else if (i == 1){
            log.info("为1,返回true.");
            return true;
        }else if (i == 2){
            log.info("为2,返回false.");
            return false;
        }else{
            //为其他
            log.info("大于2,抛出自定义异常.");
            throw new RemoteAccessException("大于2,抛出远程访问异常");
        }
    }


}
