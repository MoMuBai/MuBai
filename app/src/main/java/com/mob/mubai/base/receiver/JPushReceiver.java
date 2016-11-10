package com.mob.mubai.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by mubai on 2016/11/10.
 * 极光推送Receiver
 */

public class JPushReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LLLLLLLLLLL", "JPushInterface0" + intent.getAction());
    }
}
