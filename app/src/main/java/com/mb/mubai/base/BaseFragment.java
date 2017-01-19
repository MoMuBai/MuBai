package com.mb.mubai.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.lzw.library.utils.AppManager;
import com.lzw.library.utils.TUtil;
import com.mb.mubai.App;
import com.mb.mubai.R;
import com.mb.mubai.view.widget.LoadingDialog;

import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import rx.Subscription;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/12/20
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
public abstract class BaseFragment<T extends BasePresenter, V extends BaseModel> extends Fragment {

        public T mPresenter;

        public V mModel;

        protected Subscription subscription;

        public LoadingDialog dialog;

        protected boolean isVisible;

        public Context mContext;

        public Activity mActivity;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                AppManager.getAppManager().addFragment(this);
                return getLayout(inflater, container);
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
                super.setUserVisibleHint(isVisibleToUser);
                if (isVisibleToUser) {
                        isVisible = true;
                        onVisible();
                } else {
                        isVisible = false;
                        onInvisible();
                }
        }

        /**
         * 可见
         */
        protected void onVisible() {
                lazyLoad();
        }


        /**
         * 不可见
         */
        protected void onInvisible() {

        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
                super.onViewCreated(view, savedInstanceState);
                ButterKnife.bind(this, view);
                setLoadingDialog();
                mPresenter = TUtil.getT(this, 0);
                mModel = TUtil.getT(this, 1);
                mContext = App.getInstance();
                if (this instanceof BaseView) mPresenter.setVM(this, mModel);
                initView(view);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);
                initData();
                mActivity = getActivity();
        }


        /**
         * 加载布局
         */
        protected abstract View getLayout(LayoutInflater inflater, ViewGroup container);

        /**
         * 初始化组件
         */
        protected abstract void initView(View view);

        /**
         * 初始化数据
         */
        protected abstract void initData();


        /**
         * 延迟加载
         * 子类必须重写此方法
         */
        protected abstract void lazyLoad();


        /*
         * 设置加载对话框
         */
        public void setLoadingDialog() {
                dialog = new LoadingDialog.Builder(getActivity()).create();
        }


        /*
        * Intent传递
        */
        public void intent(Class<?> cls) {
                startActivity(new Intent(getActivity(), cls));
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }


        protected void intent(Class<?> cls, Map<String, String> map) {
                Set<String> set = map.keySet();
                Intent intent = new Intent(getActivity(), cls);
                for (String key : set) {
                        intent.putExtra(key, map.get(key));
                }
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }

        /**
         * 带返回值得跳转
         */
        protected void intentForResult(Class<?> cls, int requestCode) {
                Intent intent = new Intent(getActivity(), cls);
                startActivityForResult(intent, requestCode);
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }

        protected void intentForResult(Class<?> cls, Map<String, String> map, int requestCode) {
                Set<String> set = map.keySet();
                Intent intent = new Intent(getActivity(), cls);
                for (String key : set) {
                        intent.putExtra(key, map.get(key));
                }
                startActivityForResult(intent, requestCode);
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }

        /*
         * startActivityForResult返回跳转
         */
        protected void intentSetResult(Map<String, String> map, int resultCode) {
                Set<String> set = map.keySet();
                Intent intent = new Intent();
                for (String key : set) {
                        intent.putExtra(key, map.get(key));
                }
                getActivity().setResult(resultCode, intent);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        }

        @Override
        public void onDestroy() {
                super.onDestroy();
                ButterKnife.unbind(this);
                if (mPresenter != null) mPresenter.onDestory();
                AppManager.getAppManager().removeFragment(this);
        }
}
