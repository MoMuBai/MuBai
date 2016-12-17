package com.mob.mubai.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.mubai.App;
import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.L;
import com.mob.mubai.base.utils.OkHttpClientUtil;
import com.mob.mubai.base.utils.SpUtils;
import com.mob.mubai.data.bean.LoginValue;
import com.mob.mubai.ui.contract.LoginContract;
import com.mob.mubai.ui.model.LoginModel;
import com.mob.mubai.ui.presenter.LoginPresenter;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by lzw on 2016/11/7.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

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
        @Bind(R.id.img1)
        ImageView img1;
        @Bind(R.id.txt1)
        TextView txt1;
        @Bind(R.id.back)
        RelativeLayout back;
        @Bind(R.id.img2)
        TextView img2;
        @Bind(R.id.rl)
        RelativeLayout rl;
        private String BASE_URL = "http://webim.demo.rong.io/";
        private String token;

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
                        if (userName.length() <= 0 || passWord.length() <= 0) {
                                Snackbar.make(floatBtn, "需要用户名和密码", Snackbar.LENGTH_SHORT)
                                          .setAction("返回", v1 -> {
                                                  finish();
                                          }).show();
                        }
                        if (userName.length() < 6) {
                                textInputName.setError("用户名不能少于6位");
                        } else {
                                textInputName.setError("");
                                myLogin();
                                mPresenter.login(userName, passWord);
                        }
                });
        }

        @OnClick(R.id.back)
        void back() {
                finish();
        }

        @Override
        public void showData(String data) {

        }

        @Override
        public void login(LoginValue loginValue) {

        }

        @Override
        public void showJackson() {

        }

        /**
         * 用户登录，用户登录成功，获得 cookie，将cookie 保存
         */
        private void myLogin() {
                Map<String, String> requestParameter = new HashMap<String, String>();

                requestParameter.put("email", "yang115@qq.com");
                requestParameter.put("password", "123456");

                OkHttpClientUtil.postAsyn(BASE_URL + "email_login", new OkHttpClientUtil.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                                L.d(TAG, "onResponse() called with: response = [" + response + "]");
                                getToken();
                        }
                }, requestParameter);
        }

        /**
         * 获得token
         */
        private void getToken() {
                OkHttpClientUtil.getAsyn(BASE_URL + "token", new OkHttpClientUtil.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                                token = response;
                                L.d("LoginActivity", token);
                                connect(token);
                                SpUtils.putString(LoginActivity.this, "token", "token");
                        }
                });
        }


        /**
         * 建立与融云服务器的连接
         *
         * @param token
         */
        private void connect(String token) {

                if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

                        /**
                         * IMKit SDK调用第二步,建立与服务器的连接
                         */
                        RongIM.connect(token, new RongIMClient.ConnectCallback() {

                                /**
                                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                                 */
                                @Override
                                public void onTokenIncorrect() {
                                        L.d("LoginActivity", "--onTokenIncorrect");
                                }

                                /**
                                 * 连接融云成功
                                 * @param userid 当前 token
                                 */
                                @Override
                                public void onSuccess(String userid) {
                                        L.d("LoginActivity", "--onSuccess" + userid);
                                        startActivity(new Intent(LoginActivity.this, IMActivity.class));
                                        finish();
                                }

                                /**
                                 * 连接融云失败
                                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                                 */
                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {
                                        L.d("LoginActivity", "--onError" + errorCode);
                                }
                        });
                }
        }
}
