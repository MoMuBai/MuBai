package com.lzw.ys7.uikit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ezvizuikit.open.EZUIError;
import com.ezvizuikit.open.EZUIKit;
import com.ezvizuikit.open.EZUIPlayer;
import com.lzw.ys7.R;

import java.util.Calendar;

/**
 * @author: lzw
 * @date: 13/12/2017 11:47 AM
 * @desc:
 */

public class UIKitActivity extends AppCompatActivity implements EZUIPlayer.EZUIPlayerCallBack {

    private EZUIPlayer ezuiPlayer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uikit);
        ezuiPlayer = (EZUIPlayer) findViewById(R.id.player_ui);
        /**
         * 设置播放回调CallBack
         */
        ezuiPlayer.setCallBack(this);
        /**
         * 设置播放区域宽高
         */
//        ezuiPlayer.setSurfaceSize(100, 100);
        /**
         * 设置播放参数
         */
        ezuiPlayer.setUrl("");
    }

    @Override
    public void onPlaySuccess() {

    }

    @Override
    public void onPlayFail(EZUIError ezuiError) {
        Log.d("UIKitActivity", "ErrorStr:" + ezuiError.getErrorString() + "ErrorCode:" + ezuiError.getInternalErrorCode());
    }

    @Override
    public void onVideoSizeChange(int i, int i1) {

    }

    @Override
    public void onPrepared() {
        /**
         * 开始播放
         */
        ezuiPlayer.startPlay();
    }

    @Override
    public void onPlayTime(Calendar calendar) {

    }

    @Override
    public void onPlayFinish() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        /**
         * 暂停播放
         */
        ezuiPlayer.stopPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 释放资源
         */
        ezuiPlayer.releasePlayer();
    }
}
