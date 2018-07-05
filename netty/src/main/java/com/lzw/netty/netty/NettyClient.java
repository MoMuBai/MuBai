package com.lzw.netty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpChannel;

/**
 * Author: lzw
 * Date: 2018/7/4
 * Description: NettyClient类是对Netty进行封装，用于建立连接、断开连接、异常断开重连以及向服务器发送消息。
 */

public class NettyClient {
    private static NettyClient nettyClient = new NettyClient();

    private EventLoopGroup group;

    private NettyListener nettyListener;

    private Channel channel;

    /**
     * 是否连接
     */
    private boolean isConnect = false;

    /**
     * 重连次数
     */
    private int reconnectNum = Integer.MAX_VALUE;

    /**
     * 重连时间
     */
    private long reconnectIntervalTime = 5000;

    public static NettyClient getInstance() {
        return nettyClient;
    }

    public synchronized NettyClient connect() {
        if (!isConnect) {
            group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap().group(group)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioSctpChannel.class)
                    .handler(new NettyClientHandler(nettyListener));
            try {
                bootstrap.connect("", 1).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            isConnect = true;
                            channel = future.channel();
                        } else {
                            isConnect = false;
                        }
                    }
                }).sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    public void disconnect() {
        group.shutdownGracefully();
    }


    public void reconnect() {
        if (reconnectNum > 0 && !isConnect) {
            reconnectNum--;
            try {
                Thread.sleep(reconnectIntervalTime);
            } catch (InterruptedException e) {
                disconnect();
                connect();
            }
        } else {
            disconnect();
        }
    }


    public boolean sendMsgToServer(byte[] data, ChannelFutureListener listener) {
        boolean flag = channel != null && isConnect;
        if (flag) {
            ByteBuf byteBuf = Unpooled.copiedBuffer(data);
            channel.writeAndFlush(byteBuf).addListener(listener);
        }
        return flag;
    }

    public void setReconnectNum(int reconnectNum) {
        this.reconnectNum = reconnectNum;
    }

    public void setReconnectIntervalTime(long reconnectIntervalTime) {
        this.reconnectIntervalTime = reconnectIntervalTime;
    }

    public boolean getConnectStatus() {
        return isConnect;
    }

    public void setConnectStatus(boolean status) {
        this.isConnect = status;
    }

    public void setListener(NettyListener listener) {
        this.nettyListener = listener;
    }
}

