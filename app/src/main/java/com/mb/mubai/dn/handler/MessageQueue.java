package com.mb.mubai.dn.handler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is MessageQueue
 */

public class MessageQueue {

    /**
     * 通过数组的结构存储Message对象
     */
    private Message[] items;

    /**
     * 入队和出队的索引位置
     */
    private int putIndex, takeIndex;

    /**
     * 计数器
     */
    private int count;

    /**
     * 通过Lock去加锁
     */
    private Lock lock;

    /**
     * 条件变量
     */
    private Condition notEmpty, notFull;

    public MessageQueue() {
        items = new Message[50];//设置大小。50个
        lock = new ReentrantLock();//可重入锁
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    /**
     * 运行在子线程，生产者
     *
     * @param message
     */
    public void enqueueMessage(Message message) {
        try {
            lock.lock();
            //消息队列满了等待消费
            while (count == 50) {
                notFull.await();
            }
            items[putIndex] = message;
            putIndex = (++putIndex == items.length) ? 0 : putIndex;
            count++;
            //队列不为空，通知主线程消费
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    /**
     * 运行在主线程，消费者
     *
     * @return
     */
    public Message next() {
        Message message = null;
        try {
            lock.lock();
            //如果队列为空，等待加入
            while (count == 0) {
                notEmpty.await();
            }
            message = items[takeIndex];
            items[takeIndex] = null;//取出来要置空
            takeIndex = (++takeIndex == items.length) ? 0 : takeIndex;
            count--;
            //队列不满，通知子线程生产
            notFull.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return message;
    }
}
