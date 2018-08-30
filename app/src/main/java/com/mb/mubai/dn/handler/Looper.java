package com.mb.mubai.dn.handler;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is Looper
 */

public class Looper {

    MessageQueue messageQueue;

    /**
     * 线程隔离数据
     */
    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    private Looper() {
        messageQueue = new MessageQueue();
    }

    /**
     * 初始化Looper对象，必须在主线程，进程间的东西是可以
     */
    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * 提供一个Looper对象
     *
     * @return
     */
    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    /**
     * 轮询器，主线程中调用
     */
    public static void loop() {
        Looper me = Looper.myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper；Looper.prepare()");
        }
        MessageQueue messageQueue = me.messageQueue;
        for (; ; ) {
            Message message = messageQueue.next();
            if (message == null) {
                continue;
            }
            message.target.dispatchMessage(message);
        }
    }
}
