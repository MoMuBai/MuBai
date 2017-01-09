package com.mob.mubai.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mob.mubai.ui.fragment.Fragment1;
import com.mob.mubai.ui.fragment.Fragment2;
import com.mob.mubai.ui.fragment.Fragment3;
import com.mob.mubai.ui.fragment.Fragment4;

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
