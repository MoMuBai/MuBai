package com.mfyk.mfdemotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mfyk.mfsdk.MfUnity;
import com.mfyk.mfsdk.base.MfConfig;
import com.mfyk.mfsdk.base.U3DBean;
import com.mfyk.mfsdk.callback.MfHomeCallBack;
import com.mfyk.mfsdk.callback.MfHouseCallBack;
import com.mfyk.mfsdk.callback.MfUnityCallBack;

public class MainActivity extends UnityActivity implements MfHomeCallBack, MfHouseCallBack {
    private FrameLayout top_container;
    private Button full_screen;

    //项目id  测试楼盘
    private String proId = "201708240001";

    //3d场景加载完毕标志
    private boolean u3dLoadDone = false;

    /**
     * 音乐开关状态 0：关闭状态  1：打开状态  2:隐藏开关
     */
    private String musicState = "1";

    /**
     * 场景加载方式
     * 0:加载大场景和毛坯,1:加载毛坯,8:加载AR,9:加载在线讲盘
     */
    private String sceneLoadMode = "0";

    /**
     * 当前用户类型
     * 1:操作者；2：被操作
     */
    private String userType = "1";

    private String u3Djson;
    //竖屏状态下屏幕宽度
    private int width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        int sw = DensityUtils.getPxWidth(this);
        int sh = DensityUtils.getPxHeight(this);
        width = (sw > sh) ? sh : sw;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
        top_container.setLayoutParams(layoutParams);

        mfUnity = new MfUnity(this);
        mfUnity.initSDK("e8da0370d13ba350520239c5fd51c4f8",
                "1750dd2094d009519e125efabb57903e", new MfUnityCallBack() {
                    @Override
                    public void callBack(final String code) {
//                1000 成功
//                0 美房圈sdk网络请求错误
//                99 系统繁忙
//                900101 美房圈sdk参数appKey有误
//                900102 美房圈sdk参数appSecret有误
//                900103 美房圈sdk参数访问类型有误
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (TextUtils.equals("1000", code)) {
                                    initUnity();
                                    Toast.makeText(MainActivity.this, "初始化成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "初始化失败： " + code, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
        full_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (u3dLoadDone) {
                    doFullScreen();
                } else {
                    Toast.makeText(MainActivity.this, "请耐心等待3d场景加载完毕！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findView() {
        top_container = findViewById(R.id.top_container);
        full_screen = findViewById(R.id.full_screen);
    }

    private void initUnity() {
        top_container.removeAllViews();
        mfUnity.initView(top_container);
        mfUnity.unityRequestFocus();
        U3DBean u3d = new U3DBean();
        u3d.setProjectID(proId);
        u3d.setDataServer(MfConfig.UNITY_URL_3D);
        u3d.setSceneLoadMode(sceneLoadMode);
        u3d.setUserType(userType);
        u3Djson = JSON.toJSONString(u3d);
        mfUnity.load(u3Djson, proId);
    }

    /**
     * unity全屏
     */
    private void doFullScreen() {
        full_screen.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) top_container.getLayoutParams();
        params1.addRule(RelativeLayout.CENTER_HORIZONTAL);
        int s_w = DensityUtils.getPxWidth(this);
        int s_h = DensityUtils.getPxHeight(this);
        if (s_w < s_h) {
            params1.width = DensityUtils.getPxHeight(this) + DensityUtils.getNavigationBarHeight(this);
            params1.height = DensityUtils.getPxWidth(this);
        } else {
            params1.height = DensityUtils.getPxHeight(this);
            params1.width = DensityUtils.getPxWidth(this);
        }
        top_container.setLayoutParams(params1);

        mfUnity.landscape(musicState);
    }

    /**
     * 加载项目完毕回调
     */
    @Override
    public void unityLoadDone() {
        u3dLoadDone = true;
        Toast.makeText(this, "加载完毕", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unityLandscape() {
        Toast.makeText(this, "横屏", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unityProtrait() {
        Toast.makeText(this, "竖屏", Toast.LENGTH_SHORT).show();
        full_screen.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        top_container.setLayoutParams(layoutParams);
    }

    /**
     * 卸载项目完毕回调
     */
    @Override
    public void unityUnLoadDone() {
        Toast.makeText(this, "卸载完毕", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void unitySetMusic(String musicState) {
        Toast.makeText(this, "当前返回音乐开关指示状态：" + musicState, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unityOpenRoomTypeDone() {
        Toast.makeText(this, "打开指定户型完毕回调", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unityBackRoomTypeDone() {
        Toast.makeText(this, "户型返回主页大场景", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void unityShareRoom(String roomId) {
        Toast.makeText(this, "分享按钮,户型id：" + roomId, Toast.LENGTH_SHORT).show();
    }
}
