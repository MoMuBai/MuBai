package com.lzw.netty.netty;

import io.netty.buffer.ByteBuf;

/**
 * Author: lzw
 * Date: 2018/7/4
 * Description: NettyListener类用于监听连接的状态和消息响应
 */

public interface NettyListener {

    byte STATUS_CONNECT_SUCCESS = 1;

    byte STATUS_CONNECT_CLOSED = 0;

    byte STATUS_CONNECT_ERROR = 0;

    /**
     * 对消息的处理
     */
    void onMessageResponse(ByteBuf byteBuf);

    /**
     * 当服务状态发生变化时触发
     */
    void onServiceStatusConnectChanged(int statusCode);

}
