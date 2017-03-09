package com.mb.mubai.ui.msg;

import android.support.v4.app.Fragment;
import android.view.View;

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
public class MsgFragment extends BaseFragment {


        public static Fragment getFragment() {
                Fragment f = new MsgFragment();
                return f;
        }


        @Override
        protected int getLayoutId() {
                return R.layout.fragment_message;
        }

        @Override
        protected void initView(View view) {

        }

        @Override
        protected void initData() {

        }

        @Override
        protected void lazyLoad() {

        }
}
