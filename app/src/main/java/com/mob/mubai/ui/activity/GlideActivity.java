package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.mglide.MGlideHelper;
import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.api.ApiClient;
import com.mob.mubai.base.utils.L;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/12/23
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  引导页
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */

public class GlideActivity extends BaseActivity {

        @Bind(R.id.glide_helper)
        MGlideHelper mGlideHelper;

        private List<ImageView> viewList;

        @Override
        protected int getLayout() {
                return R.layout.activity_glide;
        }

        @Override
        protected void initView() {
                viewList = new ArrayList<>();
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.mubai);
                viewList.add(imageView);
                viewList.add(imageView);
                viewList.add(imageView);
                mGlideHelper.setBgView(viewList);
        }

        @Override
        protected void initData() {
                //OkHttp请求
                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
                Request request = new Request.Builder()
                          .addHeader("version", "1.0.2")
                          .addHeader("versioncode", "5")
                          .url("")
                          .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                L.d("Exception:" + e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                                L.d("response:" + request.body().toString());
                        }
                });
                //Retrofit请求
                ApiClient.getService().getStr("name");
        }
}
