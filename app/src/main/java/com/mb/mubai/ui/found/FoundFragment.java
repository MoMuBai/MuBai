package com.mb.mubai.ui.found;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzw.library.utils.StatusBarCompat;
import com.lzw.library.utils.To;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseFragment;

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

public class FoundFragment extends BaseFragment<FoundPresenter, FoundModel> implements FoundContract.View {

    private boolean isHide = true;

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.toggle)
    AppCompatButton toggle;

    public static Fragment getFragment() {
        Fragment f = new FoundFragment();
        return f;
    }


    @Override
    protected FoundModel getModel() {
        return new FoundModel();
    }

    @Override
    protected FoundPresenter getPresenter() {
        return new FoundPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_found;
    }

    @Override
    protected void initView(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            text.setVisibility(View.VISIBLE);
            toggle.setVisibility(View.VISIBLE);
        } else {
            text.setVisibility(View.GONE);
            toggle.setVisibility(View.GONE);
        }


        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusBarCompat.translucentStatusBar(getActivity(), isHide);
                isHide = !isHide;
            }
        });

        toggle.callOnClick();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoad() {
//        mPresenter.getData("Found");
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
            StatusBarCompat.translucentStatusBar(getActivity(), isHide);
            isHide = !isHide;
        }
    }

}
