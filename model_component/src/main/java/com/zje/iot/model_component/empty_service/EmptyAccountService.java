package com.zje.iot.model_component.empty_service;

import com.zje.iot.model_component.service.AccountService;

/**
 * Author: lzw
 * Date: 2018/7/31
 * Description: This is EmptyAccountService
 */

public class EmptyAccountService implements AccountService {
    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public String getAccountId() {
        return null;
    }
}
