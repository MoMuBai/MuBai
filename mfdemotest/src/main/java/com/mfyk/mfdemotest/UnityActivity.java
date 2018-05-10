package com.mfyk.mfdemotest;

import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.mfyk.mfsdk.MfUnity;


public class UnityActivity extends AppCompatActivity {

    protected MfUnity mfUnity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This makes xperia play happy
        getWindow().setFormat(PixelFormat.RGBX_8888);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mfUnity.onUnityStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mfUnity.onUnityPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mfUnity.onUnityResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mfUnity.onUnityStop();
    }

    @Override
    protected void onDestroy() {
        mfUnity.onUnityDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mfUnity.onUnityLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        mfUnity.onUnityTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // This ensures the layout will be correct.
        mfUnity.onUnityConfigurationChanged(newConfig);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        // Notify Unity of the focus change.
        mfUnity.onUnityWindowFocusChanged(hasFocus);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // For some reason the multiple keyevent type is not supported by the ndk.
        // Force event injection by overriding dispatchKeyEvent().
        mfUnity.dispatchKeyEventUnity(this, event);
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // Pass any events not handled by (unfocused) views straight to UnityPlayer
        mfUnity.onKeyUpUnity(this, keyCode, event);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mfUnity.onKeyDownUnity(this, keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mfUnity.onTouchEventUnity(this, event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        /*API12*/
        mfUnity.onGenericMotionEventUnity(this, event);
        return super.onGenericMotionEvent(event);
    }
}
