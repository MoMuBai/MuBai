package com.lzw.netty.netty;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import io.netty.buffer.ByteBuf;

/**
 * Author: lzw
 * Date: 2018/7/4
 * Description: This is NettyService
 */

public class NettyService extends Service implements NettyListener {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onMessageResponse(ByteBuf byteBuf) {

    }

    @Override
    public void onServiceStatusConnectChanged(int statusCode) {

    }
}
