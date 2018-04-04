package com.lzw.ys7.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.bean.EZAccessToken;

/**
 * @author: lzw
 * @date: 13/12/2017 3:18 PM
 * @desc: 用于接收网络变化刷新SDK网络状态，接收中间页登录成功消息启动其他界面
 */

public class EzBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().endsWith("com.videogo.action.OAUTH_SUCCESS_ACTION")) {
            EZAccessToken accessToken = EZOpenSDK.getInstance().getEZAccessToken();
            String token = accessToken.getAccessToken();
            Toast.makeText(context, token, Toast.LENGTH_SHORT).show();
        }
    }
}
