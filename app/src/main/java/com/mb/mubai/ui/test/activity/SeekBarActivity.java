package com.mb.mubai.ui.test.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzw.library.utils.To;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.ui.test.contract.SeekBarContract;
import com.mb.mubai.ui.test.model.SeekBarModel;
import com.mb.mubai.ui.test.presenter.SeekBarPresenter;
import com.mb.mubai.view.widget.ShapedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.seek_bar)
    SeekBar seekBar;
    @Bind(R.id.seek_bar_other)
    SeekBar seekBarOther;
    @Bind(R.id.activity_second)
    LinearLayout activitySecond;
    @Bind(R.id.image)
    ShapedImageView image;

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

    @OnClick(R.id.back)
    void back() {
        finish();
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
}
