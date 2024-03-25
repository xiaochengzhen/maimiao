package com.example.bio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        //客户端启动必备
        Socket socket = null;
        //实例化与服务端通信的输入输出流
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        //服务器的通信地址
        InetSocketAddress addr
                = new InetSocketAddress("127.0.0.1",8888);
        try{
            socket = new Socket();
            socket.connect(addr);//连接服务器
            System.out.println("Connect Server success!!");
            //OUT INT 和服务端的要相反，如果顺序不对会等待
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Ready send message.....");
            /*向服务器输出请求*/
            output.writeUTF("Mark");
            output.flush();

            //接收服务器的输出
            System.out.println(input.readUTF());
        }finally{
            if (socket!=null) socket.close();
            if (output!=null) output.close();
            if (input!=null) input.close();

        }
    }

}
