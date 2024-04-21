package com.example.netty1.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息同步处理
 */
public class MyClientChannelSyn extends SimpleChannelInboundHandler<ByteBuf> {

    private final ConcurrentHashMap<String, BlockingQueue<Object>> responseMap
            = new ConcurrentHashMap<String, BlockingQueue<Object>>();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf o) throws Exception {
        System.out.println("客户端 收到"+ o.toString(StandardCharsets.UTF_8));
        BlockingQueue<Object> msgQueue = responseMap.get("消息唯一id");
        msgQueue.put(o.toString(StandardCharsets.UTF_8));
        channelHandlerContext.close().sync();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        BlockingQueue<Object> msgQueue = new ArrayBlockingQueue<>(1);
        responseMap.put("发送的唯一id",msgQueue);
        String ss = "hello Netty";
        System.out.println("客户端发送"+ss);
        ctx.writeAndFlush(Unpooled.copiedBuffer(ss.getBytes(StandardCharsets.UTF_8)));
        Object result =  msgQueue.take();
        System.out.println("获取到服务端的处理结果"+result);

    }
}
