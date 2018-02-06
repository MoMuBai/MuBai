package com.mb.mubai.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lzw.library.utils.MD5Util;
import com.lzw.library.utils.StatusBarCompat;
import com.lzw.library.utils.To;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/12/21
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class HomeFragment extends BaseFragment<HomePresenter, HomeModel> implements HomeContract.View {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.seek_bar)
    SeekBar seekBar;
    private int DEFAULT_COLOR = Color.parseColor("#319bd2");

    public static Fragment getFragment() {
        Fragment f = new HomeFragment();
        return f;
    }

    @Override
    protected HomeModel getModel() {
        return new HomeModel();
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        setStatusBar();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                StatusBarCompat.setStatusBarColor(getActivity(), DEFAULT_COLOR, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    protected void initData() {
        Log.d("HomeFragment", MD5Util.getMD5Str("筑家易"));
    }

    @Override
    protected void lazyLoad() {
//        mPresenter.getData("userId");
    }

    @Override
    public void showData(String data) {
        To.d(data);
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
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            setStatusBar();
        }
    }

    private void setStatusBar() {
        StatusBarCompat.setStatusBarColor(getActivity(), Color.GREEN);
        StatusBarCompat.setStatusBarColor(getActivity(), DEFAULT_COLOR);
    }
}
