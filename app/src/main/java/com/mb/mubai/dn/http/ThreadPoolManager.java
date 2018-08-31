package com.mb.mubai.dn.http;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: lzw
 * Date: 2018/8/30
 * Description: 整体的处理中心
 */

public class ThreadPoolManager {

    //单例
    private static ThreadPoolManager instance = new ThreadPoolManager();


    public static ThreadPoolManager getInstance() {
        return instance;
    }


    //1.要把调用层传入的任务放到请求队列中,
    // 对于队列的话，它的容量应该无限大，同时它要支持插入动作次数很多，还应该要有阻塞
    private LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public void execute(Runnable runnable) {
        if (null != runnable) {
            try {
                queue.put(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //2.把队列中有的任务放到线程池进行执行
    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolManager() {
        threadPoolExecutor = new ThreadPoolExecutor(4, 20, 15, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4), rejectedExecutionHandler);
        threadPoolExecutor.execute(runnable);
    }

    /**
     * 被扔出来的线程会进入到这个handler中
     */
    private RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                //重新将Runnable加入到queue中
                queue.put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    //3.让1和2无线运行下去
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                Runnable runnable = null;
                try {
                    runnable = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (runnable != null) {
                    threadPoolExecutor.execute(runnable);
                }
            }
        }
    };
}
