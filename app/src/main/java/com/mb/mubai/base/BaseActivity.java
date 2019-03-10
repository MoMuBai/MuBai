package com.mb.mubai.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lzw.library.utils.AppManager;
import com.mb.mubai.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author lzw
 * @date 2016/11/7
 */

public abstract class BaseActivity<T extends BasePresenter, M extends BaseModel> extends AppCompatActivity {

    public T mPresenter;

    public M mModel;

    protected String TAG = getClass().getName();

    public Context mContext;

    public Activity mActivity;

    protected Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//                requestWindowFeature(Window.FEATURE_NO_TITLE);
//                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                          WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayout());
        AppManager.getAppManager().addActivity(this);
        bind = ButterKnife.bind(this);
        mContext = App.getInstance();
        mActivity = this;
        mPresenter = getPresenter();
        mModel = getModel();
        if (this instanceof BaseView) {
            if (null != mPresenter && null != mModel) {
                mPresenter.setVM(mModel, this);
            }
        }
        initView();
        initData();
    }

    protected abstract M getModel();

    protected abstract T getPresenter();

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
        bind.unbind();
        AppManager.getAppManager().removeActivity(this);
    }


}
