package com.example.ebangbase.v189;
import com.jcraft.jsch.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SFTPUploadExample {

    public static void main(String[] args) {
        String username = "brokertest";
        String password = "brokertest";
        String host = "172.16.78.13";
        int port = 20888;
        File localFile = new File("D:\\ebang\\ebang-demo\\ebang-base\\src\\main\\resources\\1.txt");
        String remoteFilePath = "/upload/bcan/1.txt";
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            // 上传文件
            InputStream inputStream = new FileInputStream(localFile);
            channel.put(inputStream, remoteFilePath);
            channel.disconnect();
            session.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}

