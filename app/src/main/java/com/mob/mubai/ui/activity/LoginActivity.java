package com.mob.mubai.ui.activity;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.ui.contract.LoginContract;

/**
 * Created by mubai on 2016/11/7.
 */

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View{


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void showInfo() {

    }
}
