package com.mb.mubai.base.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mb.mubai.R;
import com.mb.mubai.ui.main.MainActivity;

/**
 * @author: lzw
 * @date: 2017/9/4 下午2:00
 * @desc:
 */

public class TestForeGroundService extends Service {
    private static final String TAG = TestForeGroundService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        Intent intent1 = new Intent(this, MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this,0,intent1,0))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("title")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("content")
                .setWhen(System.currentTimeMillis());//设置发生的时间
        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND;
        startForeground(110,notification);//开启前台服务 通知的唯一标识，通知消息
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        stopForeground(true);//停止前台服务 true表示是否移除之前的通知
        super.onDestroy();
    }

}
