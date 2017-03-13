package com.mb.mubai.ui.found;

import android.support.v4.app.Fragment;
import android.view.View;

import com.lzw.library.utils.To;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseFragment;


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


    public static Fragment getFragment() {
        Fragment f = new FoundFragment();
        return f;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_found;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoad() {
        mPresenter.getData("Found");
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
}
