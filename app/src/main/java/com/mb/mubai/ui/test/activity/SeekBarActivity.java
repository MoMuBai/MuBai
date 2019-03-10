package com.mb.mubai.ui.test.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzw.library.processor.HttpCallBack;
import com.lzw.library.processor.HttpHelper;
import com.lzw.library.utils.To;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.data.ResultData;
import com.mb.mubai.ui.test.contract.SeekBarContract;
import com.mb.mubai.ui.test.model.SeekBarModel;
import com.mb.mubai.ui.test.presenter.SeekBarPresenter;
import com.mb.mubai.view.widget.ShapedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;


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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/4
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  SeekBar
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */

public class SeekBarActivity extends BaseActivity<SeekBarPresenter, SeekBarModel> implements SeekBarContract.View {


    @BindView(R.id.seek_bar)
    SeekBar seekBar;
    @BindView(R.id.seek_bar_other)
    SeekBar seekBarOther;
    @BindView(R.id.image)
    ShapedImageView image;
    @BindView(R.id.camera_text)
    TextView cameraText;
    @BindView(R.id.activity_second)
    LinearLayout activitySecond;

    @Override
    protected SeekBarModel getModel() {
        return new SeekBarModel();
    }

    @Override
    protected SeekBarPresenter getPresenter() {
        return new SeekBarPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_seekbar;
    }

    @Override
    protected void initView() {
        cameraText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://v.juhe.cn/toutiao/index";
                HashMap<String, Object> params = new HashMap<>();
                params.put("type", "top");
                params.put("key", "APPKEY");


                HttpHelper.obtain().post(url, params, new HttpCallBack<ResultData>() {
                    @Override
                    public void onSuccess(ResultData data) {
                        Toast.makeText(mContext, data.getResultcode(), Toast.LENGTH_SHORT).show();
                    }
                });

//                if (PermissionUtils.isCameraPermission(SeekBarActivity.this, 0x007)) {
//                    Intent intent = new Intent(mContext, CameraActivity.class);
//                    startActivityForResult(intent, 10);
//                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.myPost();
//                mPresenter.start();
        Glide.with(this).load("http://o7ej9iew1.bkt.clouddn.com/171129033759019.jpg").into(image);
        Gson gson = new Gson();


        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray.put("123456");
            jsonObject1.put("0", jsonArray);
            jsonObject.put("taskDevices", jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = gson.toJson(jsonObject);
        Log.d("SeekBarActivity", json);
    }

    @Override
    public void show(String str) {
        To.d(str);
    }

    @Override
    public void show() {

    }

    @Override
    public void showStart() {

    }

    @Override
    public void showNoData(String msg) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showStop() {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0x007:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(mContext, CameraActivity.class);
                    startActivityForResult(intent, 10);
                } else {
                    Toast.makeText(this, "拍照权限被拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
