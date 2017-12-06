package com.mb.mubai.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lzw.library.utils.AppManager;
import com.lzw.library.utils.TUtil;
import com.mb.mubai.App;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by lzw on 2016/11/7.
 */

public abstract class BaseActivity<T extends BasePresenter, M extends BaseModel> extends RxAppCompatActivity {

    public T mPresenter;

    public M mModel;

    protected String TAG = getClass().getName();

    public Context mContext;

    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//                requestWindowFeature(Window.FEATURE_NO_TITLE);
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                          WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayout());
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);

        mPresenter = TUtil.getT(this, 0);

        mModel = TUtil.getT(this, 1);

        mContext = App.getInstance();

        mActivity = this;

        if (this instanceof BaseView) {
            mPresenter.setVM(mModel, this);
        }

        initView();

        initData();

    }

    protected abstract @LayoutRes
    int getLayout();


    protected abstract void initView();


    protected abstract void initData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
        AppManager.getAppManager().removeActivity(this);
    }


}
