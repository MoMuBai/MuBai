package com.lzw.ys7.sdk;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ezviz.player.EZFECMediaPlayer;
import com.ezviz.player.EZMediaPlayer;
import com.lzw.ys7.MyListView;
import com.lzw.ys7.OkHttpClientUtil;
import com.lzw.ys7.R;
import com.lzw.ys7.Ys7App;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.okhttp.Request;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZConstants;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZOpenSDKListener;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZDetectorInfo;
import com.videogo.openapi.bean.EZDeviceInfo;
import com.videogo.openapi.bean.EZProbeDeviceInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lzw
 * @date: 13/12/2017 3:24 PM
 * @desc:
 */

public class SDKActivity extends AppCompatActivity {

    private ListAdapter listAdapter;
    private String token;

    private MyListView listView;
    private List<EZDeviceInfo> ezDeviceInfos;
    private EZOpenSDK ezOpenSDK;
    private String deviceSerial = "756067560", validateCode = "SMRGKG";

    private SurfaceView mRealPlaySv;
    private SurfaceHolder mRealPlaySh = null;
    private EZPlayer ezPlayer;
    private EZProbeDeviceInfo ezProbeDeviceInfo = null;

    private EditText nameEdit, pwdEdit;
    private TextView nameText;
    private LinearLayout layout;
    private Button firstBtn, secondBtn, startBtn, screenBtn;
    private String ssid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdk);
        initPermission();
        mRealPlaySv = (SurfaceView) findViewById(R.id.surface_view);
        mRealPlaySh = mRealPlaySv.getHolder();
        listView = (MyListView) findViewById(R.id.list_view);
        nameEdit = (EditText) findViewById(R.id.name_edit);
        pwdEdit = (EditText) findViewById(R.id.pwd_edit);
        nameText = (TextView) findViewById(R.id.name_text);
        layout = (LinearLayout) findViewById(R.id.layout);
        firstBtn = (Button) findViewById(R.id.first_btn);
        screenBtn = (Button) findViewById(R.id.screen_btn);
        secondBtn = (Button) findViewById(R.id.second_btn);
        startBtn = (Button) findViewById(R.id.start_btn);
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getToken();
                Toast.makeText(SDKActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.add_device_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDevice();
            }
        });
        findViewById(R.id.delete_device_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDevice();
            }
        });
        findViewById(R.id.out_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ezOpenSDK.logout();
                token = EZOpenSDK.getInstance().getEZAccessToken().getAccessToken();
                Toast.makeText(SDKActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
            }
        });
        screenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextHelper.getTextHelper(SDKActivity.this).setText(nameText);
            }
        });
    }


    private void initPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int isPermission1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
            if (isPermission1 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 123);
            } else {
                Ys7App.getYs7App().initEzOpenSDK();
                ezOpenSDK = EZOpenSDK.getInstance();
                Log.d("SDKActivity", "ezOpenSDK:" + ezOpenSDK);
            }
        } else {
            Ys7App.getYs7App().initEzOpenSDK();
            ezOpenSDK = EZOpenSDK.getInstance();
            Log.d("SDKActivity", "ezOpenSDK:" + ezOpenSDK);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (grantResults.length > 0) {
            if (grantResults[0] >= 0) {
                Ys7App.getYs7App().initEzOpenSDK();
                ezOpenSDK = EZOpenSDK.getInstance();
                Log.d("SDKActivity", "ezOpenSDK:" + ezOpenSDK);
            }
        }
    }

    /**
     * 获取AccessToken
     *
     * @return
     */
    private void getToken() {
        Map<String, String> map = new HashMap<>();
        map.put("appKey", "2efac217bf674f9eb14828448e8c7816");
        map.put("appSecret", "ed39f75532e3c19a19c43f3cfb7e0588");
        OkHttpClientUtil.postAsyn("https://open.ys7.com/api/lapp/token/get", new OkHttpClientUtil.ResultCallback<TokenResponse>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(TokenResponse response) {
                token = response.getData().getAccessToken();
                Log.d("SDKActivity", token);
                ezOpenSDK.setAccessToken(token);
                getDeviceList();
                Toast.makeText(SDKActivity.this, "获取token：" + token, Toast.LENGTH_SHORT).show();
            }
        }, map);
    }


    /**
     * 添加设备
     */
    private void addDevice() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                boolean result = false;
                try {
                    ezProbeDeviceInfo = ezOpenSDK.probeDeviceInfo(deviceSerial);
                    try {
                        result = ezOpenSDK.addDevice(deviceSerial, validateCode);
                        if (result) {
                            getDeviceList();
                            Message message = new Message();
                            message.what = 3;
                            mHandler.sendMessage(message);
                            Toast.makeText(SDKActivity.this, "添加设备成功", Toast.LENGTH_SHORT).show();
                        }
                        Looper.loop();
                    } catch (BaseException e) {
                        Toast.makeText(SDKActivity.this, "添加设备：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                } catch (BaseException e) {
                    if (e.getErrorCode() == 120023) {//设备未配网
                        Message message = new Message();
                        message.what = 2;
                        mHandler.sendMessage(message);
                    } else {
                        Toast.makeText(SDKActivity.this, "添加设备：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    Looper.loop();
                }
            }
        }).start();

    }


    /**
     * 配置wifi
     */
    public void getWifi() {
        layout.setVisibility(View.VISIBLE);
        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameEdit.setVisibility(View.VISIBLE);
                pwdEdit.setVisibility(View.VISIBLE);
                startBtn.setVisibility(View.VISIBLE);
                @SuppressLint("WifiManagerLeak") WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                ssid = wifiInfo.getSSID();
                nameEdit.setText(ssid);
                startBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ezOpenSDK.startConfigWifi(SDKActivity.this, deviceSerial, ssid.replace("\"", ""), pwdEdit.getText().toString(), new EZOpenSDKListener.EZStartConfigWifiCallback() {
                            @Override
                            public void onStartConfigWifiCallback(EZConstants.EZWifiConfigStatus status) {
                                if (status == EZConstants.EZWifiConfigStatus.DEVICE_WIFI_CONNECTED) {
                                    Log.d("SDKActivity", "接收到设备连接上WIFI");
                                } else if (status == EZConstants.EZWifiConfigStatus.DEVICE_PLATFORM_REGISTED) {
                                    Log.d("SDKActivity", "接收到设备连接上PLAT信息");
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    /**
     * 移除设备
     */
    private void deleteDevice() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    boolean result = ezOpenSDK.deleteDevice(deviceSerial);
                    if (result) {
                        if (null != ezPlayer) {
                            ezPlayer.stopRealPlay();
                        }
                        Toast.makeText(SDKActivity.this, "移除设备成功", Toast.LENGTH_SHORT).show();
                        getDeviceList();
                        Looper.loop();
                    }
                } catch (BaseException e) {
                    Toast.makeText(SDKActivity.this, "移除设备：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }

    /**
     * 获取设备列表
     */
    private void getDeviceList() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                try {
                    ezDeviceInfos = ezOpenSDK.getDeviceList(0, 10);
                    if (null != ezDeviceInfos && ezDeviceInfos.size() > 0) {
                        Message message = new Message();
                        message.what = 0;
                        message.obj = ezDeviceInfos;
                        mHandler.sendMessage(message);
                        Looper.loop();
                    } else {
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                        Toast.makeText(SDKActivity.this, "没有设备", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                } catch (BaseException e) {
                    Toast.makeText(SDKActivity.this, "没有设备：" + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();
    }

    /**
     * 开始播放
     *
     * @param ezDeviceInfo
     */
    private void startPlayer(EZDeviceInfo ezDeviceInfo) {
        if (ezDeviceInfo != null) {
            if (ezPlayer == null) {
                ezPlayer = ezOpenSDK.createPlayer(ezDeviceInfo.getDeviceSerial(), ezDeviceInfo.getCameraInfoList().get(0).getCameraNo());
            }
            if (ezPlayer == null) {
                return;
            }
            if (ezDeviceInfo == null) {
                return;
            }
            if (ezDeviceInfo.getIsEncrypt() == 1) {
                ezPlayer.setPlayVerifyCode(validateCode);
            }
            ezPlayer.setHandler(mHandler);
            ezPlayer.setSurfaceHold(mRealPlaySh);
            ezPlayer.startRealPlay();
            Toast.makeText(this, "开始播放", Toast.LENGTH_SHORT).show();
        }
    }

    private EZDeviceInfo ezDeviceInfo;

    @Override
    protected void onResume() {
        super.onResume();
        if (null != ezDeviceInfo) {
            startPlayer(ezDeviceInfo);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (null != ezPlayer) {
            ezPlayer.stopRealPlay();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        ezDeviceInfo = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ezDeviceInfo = null;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    listAdapter = new ListAdapter((List<EZDeviceInfo>) msg.obj, SDKActivity.this);
                    listView.setAdapter(listAdapter);
                    listAdapter.setStart(new ListAdapter.Start() {
                        @Override
                        public void startPlayer(EZDeviceInfo deviceInfo) {
                            SDKActivity.this.ezDeviceInfo = deviceInfo;
                            SDKActivity.this.startPlayer(deviceInfo);
                        }
                    });
                    listAdapter.setStop(new ListAdapter.Stop() {
                        @Override
                        public void stopPlayer(EZDeviceInfo ezDeviceInfo) {
                            ezPlayer.stopRealPlay();
                            SDKActivity.this.ezDeviceInfo = null;
                            Toast.makeText(SDKActivity.this, "暂停播放", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 1:
                    listAdapter = new ListAdapter(new ArrayList<EZDeviceInfo>(), SDKActivity.this);
                    listView.setAdapter(listAdapter);
                    break;
                case 2:
                    getWifi();
                    break;
                case 3:
                    layout.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
            return false;
        }
    });
}
