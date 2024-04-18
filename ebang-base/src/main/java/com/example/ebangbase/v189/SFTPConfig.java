package com.example.ebangbase.v189;
/**
 * @description 
 * @author xiaobo
 * @date 2024/3/21 15:39
 */

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

//@Configuration
public class SFTPConfig {

    @Value("${sftp.host}")
    private String sftpHost;

    @Value("${sftp.port}")
    private int sftpPort;

    @Value("${sftp.username}")
    private String sftpUsername;

    @Value("${sftp.password}")
    private String sftpPassword;

    @Bean
    public Session jschSession() throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(sftpUsername, sftpHost, sftpPort);
        session.setPassword(sftpPassword);
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        session.connect();
        return session;
    }

    @Bean
    public ChannelSftp channelSftp(Session session) throws Exception {
        return (ChannelSftp) session.openChannel("sftp");
    }


    public static void main(String[] args) {
        String str = "Hello, world! 123";

        // 将字符串按照 UTF-8 编码为字节数组
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

        // 截取前10个字节的数据
        byte[] first10Bytes = new byte[10];
        System.arraycopy(bytes, 0, first10Bytes, 0, 10);

        // 打印字节数组的长度和内容
        System.out.println("字节数：" + first10Bytes.length);
        System.out.println("字节数组内容：" + new String(first10Bytes, StandardCharsets.UTF_8));
    }

}
