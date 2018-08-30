package com.mb.mubai.dn.handler;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is Handler
 */

public class Handler {

    private Looper mLooper;
    private MessageQueue messageQueue;

    public Handler() {
        mLooper = Looper.myLooper();
        messageQueue = mLooper.messageQueue;
    }

    public void sendMessage(Message message) {
        message.target = this;
        messageQueue.enqueueMessage(message);
    }

    /**
     * 主线程调用
     *
     * @param message
     */
    public void handlerMessage(Message message) {
    }

    /**
     * 主线程调用
     *
     * @param message
     */
    public void dispatchMessage(Message message) {
        handlerMessage(message);
    }
}


