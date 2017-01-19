package com.mob.mubai.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.lzw.library.utils.AppManager;
import com.lzw.library.utils.TUtil;
import com.mob.mubai.App;
import com.mob.mubai.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lzw on 2016/11/7.
 */

public abstract class BaseActivity<T extends BasePresenter, M extends BaseModel> extends AppCompatActivity {

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

                if (this instanceof BaseView) mPresenter.setVM(mModel, this);

                initView();

                initData();

        }

        protected abstract int getLayout();


        protected abstract void initView();


        protected abstract void initData();


        @Override
        protected void onDestroy() {
                super.onDestroy();
                if (mPresenter != null) mPresenter.onDestory();
                AppManager.getAppManager().removeActivity(this);
        }


}
