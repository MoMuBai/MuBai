package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.os.Process;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.AppManager;
import com.mob.mubai.ui.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


        @Bind(R.id.view_pager)
        ViewPager viewPager;
        @Bind(R.id.tab_layout)
        TabLayout tabLayout;
        @Bind(R.id.navigation_view)
        NavigationView navigationView;
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
                tabLayout.setupWithViewPager(viewPager);
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

}
