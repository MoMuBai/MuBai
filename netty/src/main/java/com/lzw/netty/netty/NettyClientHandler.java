package com.lzw.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Author: lzw
 * Date: 2018/7/4
 * Description: This is NettyClientHandler
 */

public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private NettyListener nettyListener;

    public NettyClientHandler(NettyListener nettyListener) {
        this.nettyListener = nettyListener;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        NettyClient.getInstance().setConnectStatus(true);
        nettyListener.onServiceStatusConnectChanged(NettyListener.STATUS_CONNECT_SUCCESS);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        super.channelInactive(ctx);
        NettyClient.getInstance().setConnectStatus(false);
        nettyListener.onServiceStatusConnectChanged(NettyListener.STATUS_CONNECT_CLOSED);
        NettyClient.getInstance().reconnect();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        nettyListener.onMessageResponse(msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                ctx.close();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                try {
                    ctx.channel().writeAndFlush("Chilent-Ping\r\n");
                } catch (Exception e) {

                }
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}

