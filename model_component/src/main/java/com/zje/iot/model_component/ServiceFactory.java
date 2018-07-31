package com.zje.iot.model_component;

import com.zje.iot.model_component.empty_service.EmptyAccountService;
import com.zje.iot.model_component.service.AccountService;

/**
 * Author: lzw
 * Date: 2018/7/31
 * Description: This is ServiceFactory
 */

public class ServiceFactory {
    private AccountService accountService;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return Inner.factory;
    }

    /**
     * 通过静态内部类获取ServiceFactory的实例
     */
    private static class Inner {
        private static ServiceFactory factory = new ServiceFactory();
    }


    /**
     * 返回User组件的Service实例
     *
     * @return
     */
    public AccountService getAccountService() {
        if (null == accountService) {
            accountService = new EmptyAccountService();
        }
        return accountService;
    }

    /**
     * 接收User组件实现的Service实例
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
