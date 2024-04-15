package com.example.netty1.service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class MyServerChannel extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务端收到"+byteBuf.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(byteBuf);
      //  ctx.channel().writeAndFlush(byteBuf);
      //  ctx.pipeline().writeAndFlush(byteBuf);
        ctx.close().sync();
    }
}
