package com.zje.iot.model_component.service;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Author: lzw
 * Date: 2018/7/31
 * Description: This is AccountService
 */

public interface AccountService {
    /**
     * 判断是否登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 获取用户Id
     *
     * @return
     */
    String getAccountId();

    Fragment newLoginFragment(Activity activity, int contrinerId, FragmentManager fragmentManager, Bundle bundle, String tag);
}

