package com.mob.mubai.base.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;


import com.mob.mubai.R;
import com.mob.mubai.ui.activity.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lzw on 2016/11/10.
 * 后台更新Service
 *
 */
public class UpdateService extends Service {
    private static final int TIMEOUT = 5 * 1000; // 超时
    private static final int DOWN_OK = 1;
    private static final int DOWN_ERROR = 0;
    private String mAppName, mUrls;
    private RemoteViews mContentView;
    /**
     * 通知更新
     */
    private Intent mUpdateIntent;
    private PendingIntent mPendingIntent;
    private NotificationManager mNotificationManager; // 通知管理器
    private Notification mNotification; // 通知
    private Notification.Builder builder;
    private int mNotificationId = 1;
    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = this;
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mAppName = intent.getStringExtra("appName");
        mUrls = intent.getStringExtra("url");
        try {
            getFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        createNotification();
        startUpdate();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // 获取文件的保存路径
    public File getFile() throws Exception {
        String SavePath = getSDCardPath() + "/yfh";
        File path = new File(SavePath);
        File file = new File(SavePath + "/yifuhua.apk");
        if (!path.exists()) {
            path.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }


    // 获取SDCard的目录路径功能
    private String getSDCardPath() {
        File sdcardDir = null;
        // 判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (sdcardExist) {
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }

    private void createNotification() {
        //自定义Notification视图
//        mContentView = new RemoteViews(getPackageName(), R.layout.notification_item);
//        mContentView.setTextViewText(R.id.notificationTitle, mAppName + "—正在下载");
//        mContentView.setTextViewText(R.id.notificationPercent, "0%");
//        mContentView.setProgressBar(R.id.notificationProgress, 100, 0, false);

        mUpdateIntent = new Intent(this, MainActivity.class);
        mUpdateIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mPendingIntent = PendingIntent.getActivity(this, 0, mUpdateIntent, 0);

        builder = new Notification.Builder(mContext)
                .setContentTitle("开始下载 " + mAppName)
                .setContentIntent(mPendingIntent)
                .setProgress(100, 1, false)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher);
        mNotification = builder.getNotification();

        // 发送通知
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mNotificationId, mNotification);
        // 清除通知铃声
        mNotification.defaults = 0;
    }

    /***
     * 开启线程下载更新
     */
    public void startUpdate() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 添加通知声音
                mNotification.defaults |= Notification.DEFAULT_SOUND;
                switch (msg.what) {
                    case DOWN_OK:
                        installApk();
                        break;
                    case DOWN_ERROR:
                        builder.setContentTitle(mAppName + " 下载失败");
                        builder.setContentIntent(mPendingIntent);
                        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            mNotificationManager.notify(mNotificationId, builder.build());
                        }
                }
                stopService(mUpdateIntent);
                stopSelf();
            }
        };
        // 启动线程下载更新
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    downloadUpdateFile(mUrls, getFile().toString(), handler);
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendMessage(handler.obtainMessage(DOWN_ERROR));
                }
            }
        }).start();
    }


    /***
     * 下载文件
     */
    public void downloadUpdateFile(String down_url, String file, Handler handler) throws Exception {
        int totalSize; // 文件总大小
        InputStream inputStream;
        FileOutputStream outputStream;
        URL url = new URL(down_url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(TIMEOUT);
        httpURLConnection.setReadTimeout(TIMEOUT);
        // 获取下载文件的size
        totalSize = httpURLConnection.getContentLength();
        Log.d("UpdateService", "totalSize >>> " + totalSize);
        if (httpURLConnection.getResponseCode() == 404) {
            throw new Exception("fail!");
        }
        inputStream = httpURLConnection.getInputStream();
        outputStream = new FileOutputStream(file, false);

        // 异步任务开始下载
        new UpdateAsyncTask(inputStream, outputStream, handler).execute(totalSize);
    }

    private class UpdateAsyncTask extends AsyncTask<Integer, Integer, Boolean> {

        private InputStream in;

        private FileOutputStream fos;

        private Handler handler;

        public UpdateAsyncTask(InputStream inputStream, FileOutputStream outputStream, Handler handler) {
            super();
            in = inputStream;
            fos = outputStream;
            this.handler = handler;
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            float totalSize = params[0]; // 下载总大小
            float downloadCount = 0; // 已下载大小
            int updateProgress = 0; // 更新进度
            int updateStep = 5; // 更新进度步进

            byte buffer[] = new byte[1024];
            int readsize = 0;
            try {
                while ((readsize = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, readsize);
                    // 计算已下载到的大小
                    downloadCount += readsize;
                    // 先计算已下载的百分比，然后跟上次比较是否有增加，有则更新通知进度
                    int now = (int) (downloadCount * 100 / totalSize);
                    if (updateProgress < now) {
                        updateProgress = now;
                        Log.d("UpdateService", "update: " + updateProgress + "%");
                        publishProgress(updateProgress);
                    }
                }
            } catch (Exception e) {
                Log.e("UpdateService", "download err===>\n " + e.getMessage());
                return false;
            } finally {
                try {
                    fos.close();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            //改变通知栏
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mNotificationManager.notify(mNotificationId, builder.build());
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                autoInstallApk();
                handler.sendMessage(handler.obtainMessage(DOWN_OK)); // 通知handler已经下载完成
            } else {
                handler.sendMessage(handler.obtainMessage(DOWN_ERROR)); // 通知handler下载出错
            }
            super.onPostExecute(result);
        }

    }


    /**
     * 点击安装
     */
    public void installApk() {
        // 下载完成，点击安装
        Uri uri = null;
        try {
            uri = Uri.fromFile(getFile());
        } catch (Exception e) {
            Log.e("UpdateService", "手动点击安装更新 >>> 安装失败:" + e.getMessage());
        }
        // 安装应用意图
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        mPendingIntent = PendingIntent.getActivity(UpdateService.this, 0, intent, 0);
        builder.setContentTitle(mAppName + "下载完成").setProgress(0, 0, false);
        builder.setContentIntent(mPendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mNotificationManager.notify(mNotificationId, builder.build());
        }

    }

    /**
     * 自动安装
     */
    public void autoInstallApk() {
        if (null != mContext) {
            Log.d("UpdateService", "下载完成==");
            Uri uri = null;
            try {
                uri = Uri.fromFile(getFile());
            } catch (Exception e) {
                Log.e("UpdateService", "自动更新 >>> 安装失败:" + e.getMessage());
            }
            Log.d("UpdateService", "自动更新 uri = " + uri);
            // 安装应用意图
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }
    }
}
