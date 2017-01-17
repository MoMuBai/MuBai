package com.mob.mubai.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.mob.mubai.ui.home.Fragment1;
import com.mob.mubai.ui.mb.Fragment4;
import com.mob.mubai.ui.msg.Fragment2;
import com.mob.mubai.ui.user.Fragment3;

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
                viewList.add(Fragment1.getFragment());
                viewList.add(Fragment2.getFragment());
                viewList.add(Fragment3.getFragment());
                viewList.add(Fragment4.getFragment());
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
