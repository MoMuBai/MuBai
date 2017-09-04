package com.mb.mubai.base.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;
import android.support.annotation.Nullable;

/**
 * @author: lzw
 * @date: 2017/9/4 下午2:35
 * @desc: 可以执行耗时操作的服务
 */

public class TestIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public TestIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //在这里进行耗时操作
    }
}
