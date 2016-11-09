package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.ui.contract.LoginContract;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mubai on 2016/11/7.
 */

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {

    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.text_input_name)
    TextInputLayout textInputName;
    @Bind(R.id.et_pass)
    EditText etPass;
    @Bind(R.id.text_input_pass)
    TextInputLayout textInputPass;
    @Bind(R.id.float_btn)
    FloatingActionButton floatBtn;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        textInputName.setHint("Name");
        textInputPass.setHint("Pass");
    }

    @Override
    protected void initData() {
        floatBtn.setOnClickListener(v -> {
            String userName = textInputName.getEditText().getText().toString();
            String passWord = textInputPass.getEditText().getText().toString();
            if (userName.length()<=0 || passWord.length() <=0){
                Snackbar.make(floatBtn,"需要用户名和密码",Snackbar.LENGTH_SHORT)
                        .setAction("返回",v1 -> {
                            finish();
                        }).show();
            }
            if (userName.length()<6){
                textInputName.setError("用户名不能少于6位");
            }else {
                textInputName.setError("");
            }
        });
    }

    @Override
    public void showInfo() {

    }

}
