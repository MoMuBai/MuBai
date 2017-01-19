package com.mb.mubai.ui.mb;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class Fragment4 extends BaseFragment {


        public static Fragment getFragment() {
                Fragment f = new Fragment4();
                return f;
        }

        @Override
        protected View getLayout(LayoutInflater inflater, ViewGroup container) {
                return inflater.inflate(R.layout.activity_im, container, false);
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
