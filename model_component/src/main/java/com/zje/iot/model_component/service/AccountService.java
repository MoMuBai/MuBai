package com.zje.iot.model_component.service;

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
}
