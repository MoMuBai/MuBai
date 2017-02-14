package com.mb.mubai.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.mb.mubai.ui.home.HomeFragment;
import com.mb.mubai.ui.msg.MsgFragment;
import com.mb.mubai.ui.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/25
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

public class MainAdapter extends FragmentPagerAdapter {
        private String[] titles = {"Tab1", "Tab2", "Tab3", "Tab4"};

        private List<Fragment> viewList;

        public MainAdapter(FragmentManager fm) {
                super(fm);
                viewList = new ArrayList<>();
                viewList.add(HomeFragment.getFragment());
                viewList.add(MsgFragment.getFragment());
                viewList.add(UserFragment.getFragment());
                viewList.add(UserFragment.getFragment());
        }

        @Override
        public int getCount() {
                return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
                return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
                return viewList.get(position);
        }

}
