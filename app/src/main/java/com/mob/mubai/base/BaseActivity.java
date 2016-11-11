package com.mob.mubai.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.mob.mubai.R;
import com.mob.mubai.base.utils.AppManager;
import com.mob.mubai.base.utils.TUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mubai on 2016/11/7.
 */

public abstract class BaseActivity<T extends BasePresenter,V extends BaseModel>extends AppCompatActivity{

    public T mPresenter;

    public V mModel;

    protected String TAG = getClass().getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);

        mPresenter = TUtil.getT(this, 0);

        mModel = TUtil.getT(this, 1);

        if (this instanceof BaseView) mPresenter.setVM(this, mModel);
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
        if (mPresenter != null) mPresenter.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }


}
