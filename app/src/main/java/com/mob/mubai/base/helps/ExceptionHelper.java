package com.mob.mubai.base.helps;

import android.content.Context;

import com.mob.mubai.base.utils.L;


/**
 * Created by lzw on 2016/9/29.
 * 异常处理帮助类
 */

public final class ExceptionHelper implements Thread.UncaughtExceptionHandler{

    private static ExceptionHelper instance;  //单例引用，这里我们做成单例的，因为我们一个应用程序里面只需要一个UncaughtExceptionHandler实例

    private ExceptionHelper(){}

    public synchronized static ExceptionHelper getInstance(){  //同步方法，以免单例多线程环境下出现异常
        if (instance == null){
            instance = new ExceptionHelper();
        }
        return instance;
    }

    public void init(Context ctx){  //初始化，把当前对象设置成UncaughtExceptionHandler处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) { //当有未处理的异常发生时，就会来到这里。。
        L.d("Mubai", "uncaughtException, thread: " + thread
                + " name: " + thread.getName() + " id: " + thread.getId() + "exception: "
                + ex);
        String threadName = thread.getName();
        if ("sub1".equals(threadName)) {
            L.d("Mubai", "");
        }else if("sub2".endsWith(threadName)){

            //这里我们可以根据thread name来进行区别对待，同时，我们还可以把异常信息写入文件，以供后来分析。
        }
    }
}
