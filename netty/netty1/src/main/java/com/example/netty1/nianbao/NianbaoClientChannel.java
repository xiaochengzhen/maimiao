package com.example.netty1.nianbao;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.boot.system.SystemProperties;

import java.nio.charset.StandardCharsets;

public class NianbaoClientChannel extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf o) throws Exception {
        System.out.println("客户端 收到"+ o.toString(StandardCharsets.UTF_8));
//        channelHandlerContext.close().sync();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       // String ss = "hello Netty" + System.getProperty("line.separator");
        String ss = "hello Netty" + "&";
        System.out.println("客户端发送"+ss);
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ss.getBytes(StandardCharsets.UTF_8)));
        }
    }
}
