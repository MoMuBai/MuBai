package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.os.Process;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.AppManager;
import com.mob.mubai.ui.adapter.MainAdapter;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/5
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
public class MainActivity extends BaseActivity {

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
                        switch (item.getGroupId()) {
                                case R.id.g1:
                                        startIntent(SeekBarActivity.class);
                                        break;
                                case R.id.g2:
                                        startIntent(LoginActivity.class);
                                        break;
                                case R.id.g3:
                                        startIntent(ReflectionActivity.class);
                                        break;
                                case R.id.g4:
                                        startIntent(BannerActivity.class);
                                        break;
                                case R.id.g5:
                                        startIntent(RecyclerActivity.class);
                                        break;
                                case R.id.g6:
                                        startIntent(GlideActivity.class);
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
