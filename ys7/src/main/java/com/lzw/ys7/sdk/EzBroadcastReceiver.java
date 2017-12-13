package com.lzw.ys7.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.bean.EZAccessToken;

/**
 * @author: lzw
 * @date: 13/12/2017 3:18 PM
 * @desc:
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
