package com.mob.mubai.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mob.mubai.base.utils.AppManager;

import butterknife.ButterKnife;

/**
 * Created by mubai on 2016/11/7.
 */

public abstract class BaseActivity<T extends BasePresenter>extends AppCompatActivity{

    private BasePresenter T;

    protected String TAG = getClass().getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    protected abstract int getLayout();


    protected abstract void initView();


    protected abstract void initData();


    protected void startIntent(Class<Activity> cls){
        Intent intent = new Intent();
        intent.setClass(this,cls);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }
}
