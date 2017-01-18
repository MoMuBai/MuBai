package com.mob.mubai.ui.main;

import android.content.Intent;
import android.os.Process;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.AppManager;
import com.mob.mubai.ui.login.LoginActivity;
import com.mob.mubai.ui.test.activity.BannerActivity;
import com.mob.mubai.ui.test.activity.PinnedHeadListActivity;
import com.mob.mubai.ui.test.activity.RecyclerActivity;
import com.mob.mubai.ui.test.activity.ReflectionActivity;
import com.mob.mubai.ui.test.activity.SeekBarActivity;

import butterknife.Bind;
import butterknife.OnClick;
/**
 *
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/18
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
 *
 */
public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View {
        @Bind(R.id.navigation_view)
        NavigationView navigationView;
        @Bind(R.id.view_pager)
        ViewPager viewPager;
        @Bind(R.id.tab1)
        TextView tab1;
        @Bind(R.id.tab2)
        TextView tab2;
        @Bind(R.id.tab3)
        TextView tab3;
        @Bind(R.id.tab4)
        TextView tab4;
        private MainAdapter mainAdapter;
        private int backPressTimes;


        @Override
        protected int getLayout() {
                return R.layout.activity_main;
        }

        @Override
        protected void initView() {
                navigationView.setItemIconTintList(null);
                navigationView.setNavigationItemSelectedListener(item -> {
                        Intent intent = new Intent();
                        switch (item.getGroupId()) {
                                case R.id.g1:
                                        intent.setClass(mActivity, SeekBarActivity.class);
                                        startActivity(intent);
                                        break;
                                case R.id.g2:
                                        intent.setClass(mActivity, LoginActivity.class);
                                        startActivity(intent);
                                        break;
                                case R.id.g3:
                                        intent.setClass(mActivity, ReflectionActivity.class);
                                        startActivity(intent);
                                        break;
                                case R.id.g4:
                                        intent.setClass(mActivity, BannerActivity.class);
                                        startActivity(intent);
                                        break;
                                case R.id.g5:
                                        intent.setClass(mActivity, RecyclerActivity.class);
                                        startActivity(intent);
                                        break;
                                case R.id.g6:
                                        intent.setClass(mActivity, PinnedHeadListActivity.class);
                                        startActivity(intent);
                                        break;
                                default:
                                        break;
                        }
                        return true;
                });
        }


        @Override
        protected void initData() {
                initTab();
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

        private void initTab() {
                mainAdapter = new MainAdapter(getSupportFragmentManager());
                viewPager.setAdapter(mainAdapter);
                viewPager.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                                return false;
                        }
                });
        }

        /**
         * 点击返回键的事件
         */
        @Override
        public void onBackPressed() {
                if (backPressTimes == 0) {
                        Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                        backPressTimes = 1;
                        new Thread() {
                                @Override
                                public void run() {
                                        try {
                                                Thread.sleep(2000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        } finally {
                                                backPressTimes = 0;
                                        }
                                }
                        }.start();
                        return;
                } else {
                        AppManager.getAppManager().finishAllActivity();
                        Process.killProcess(Process.myPid());
                }
                super.onBackPressed();
        }


        @OnClick({R.id.tab1, R.id.tab2, R.id.tab3, R.id.tab4})
        void onTabClick(View view) {
                switch (view.getId()) {
                        case R.id.tab1:
                                viewPager.setCurrentItem(0);
                                break;
                        case R.id.tab2:
                                viewPager.setCurrentItem(1);
                                break;
                        case R.id.tab3:
                                viewPager.setCurrentItem(2);
                                break;
                        case R.id.tab4:
                                viewPager.setCurrentItem(3);
                                break;
                        default:
                                break;
                }
        }

}
