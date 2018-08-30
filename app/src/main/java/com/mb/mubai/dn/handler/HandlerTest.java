package com.mb.mubai.dn.handler;

import android.util.Log;

import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;

import java.util.UUID;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is HandlerTest
 */

public class HandlerTest extends BaseActivity{
    public static void main() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();

                /**
                 * 线程间的通信，所有的线程之间通信
                 */
                Handler handler = new Handler() {
                    @Override
                    public void handlerMessage(Message message) {
                        Log.d("HandlerTest", "handler:" + Thread.currentThread().getName() + "msg:" + message);
                    }
                };

                for (int i = 0; i < 10; i++) {
                    new Thread() {
                        @Override
                        public void run() {
                            Message message = new Message();
                            message.what = 1;
                            message.obj = UUID.randomUUID().toString();
                            handler.sendMessage(message);
                            Log.d("HandlerTest", "send" + Thread.currentThread().getName() + "msg:" + message);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
                Looper.loop();
            }
        }.start();
    }

    @Override
    protected BaseModel getModel() {
        return null;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
