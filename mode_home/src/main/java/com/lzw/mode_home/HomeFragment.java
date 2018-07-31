package com.lzw.mode_home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.zje.iot.model_component.ServiceFactory;

/**
 * Author: lzw
 * Date: 2018/7/31
 * Description: This is HomeFragment
 */

public class HomeFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHomeData();
        getLoginFragment();
    }

    private void getLoginFragment() {
        /**
         * 通过ServiceFactory获取用户的Fragment的实例化对象
         */
        ServiceFactory.getInstance().getAccountService().newLoginFragment(getActivity(), 0, getChildFragmentManager(), null, "");
    }

    private void getHomeData() {
        /**
         * 通过ServiceFactory获取用户的信息，实现组件间的信息传递
         */
        if (ServiceFactory.getInstance().getAccountService().isLogin()) {
            String loginId = ServiceFactory.getInstance().getAccountService().getAccountId();
            Toast.makeText(getActivity(), "获取信息成功" + loginId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "用户未登录", Toast.LENGTH_SHORT).show();
        }
    }
}
