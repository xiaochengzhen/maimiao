package com.example.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(8888));
        System.out.println("Start Server ....");
        try {
            while(true){
                new Thread(new ServerTask(serverSocket.accept())).start();
            }
        } finally {
            serverSocket.close();
        }

    }

    private static class ServerTask implements Runnable{
        private Socket socket;
        public ServerTask(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            //实例化与客户端通信的输入输出流
            try(ObjectInputStream inputStream =
                        new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream =
                        new ObjectOutputStream(socket.getOutputStream())){

                //接收客户端的输出，也就是服务器的输入
                String userName = inputStream.readUTF();
                System.out.println("Accept client message:"+userName);

                //服务器的输出，也就是客户端的输入
                outputStream.writeUTF("Hello,"+userName);
                outputStream.flush();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

