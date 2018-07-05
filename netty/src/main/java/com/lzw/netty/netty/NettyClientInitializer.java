package com.lzw.netty.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * Author: lzw
 * Date: 2018/7/4
 * Description: This is NettyClientInitializer
 */

public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    private NettyListener nettyListener;

    private int WRITE_WAIT_SECONDS = 10;

    private int READ_WAIT_SECONDS = 13;

    public NettyClientInitializer(NettyListener nettyListener) {
        this.nettyListener = nettyListener;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        SslContext sslContext = SslContextBuilder.forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(sslContext.newHandler(ch.alloc()));// 开启SSL
        pipeline.addLast(new LoggingHandler(LogLevel.INFO)); // 开启日志，可以设置日志等级
        pipeline.addLast(new NettyClientHandler(nettyListener));
    }
}
