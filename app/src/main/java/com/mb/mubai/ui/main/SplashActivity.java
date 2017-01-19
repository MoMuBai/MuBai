package com.mb.mubai.ui.main;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;


import com.lzw.library.utils.AppUtil;
import com.mb.mubai.App;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.service.UpdateService;
import com.mb.mubai.view.dialog.MyDialog;

import butterknife.Bind;


/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/21
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  闪屏
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */

public class SplashActivity extends BaseActivity {

        @Bind(R.id.iv_splash)
        ImageView ivSplash;


        private MyDialog myDialog;

        @Override
        protected int getLayout() {
                return R.layout.activity_splash;
        }

        @Override
        protected void initView() {

        }

        @Override
        protected void initData() {
                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                                try {
                                        if (AppUtil.checkIsUpdate(App.getInstance())) {
                                                showDialog();
                                        } else {
                                                startMain();
                                        }
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                }, 2000);
        }

        private void showDialog() {
                MyDialog.Builder builder = new MyDialog.Builder(this, "是否更新");
                builder.setOkButton(s -> {
                        Intent intent = new Intent(SplashActivity.this, UpdateService.class);
                        intent.putExtra("appName", "MuBai");
                        intent.putExtra("url", "");
                        startService(intent);
                });
                builder.setNoButton(s -> {
                        startMain();
                });
                myDialog = builder.create();
                myDialog.setCanceledOnTouchOutside(false);
                myDialog.show();
        }

        private void startMain() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
        }
}
