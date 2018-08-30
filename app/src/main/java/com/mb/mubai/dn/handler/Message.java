package com.mb.mubai.dn.handler;

/**
 * Author: lzw
 * Date: 2018/8/22
 * Description: This is Message
 */

public class Message {

    public int what;

    public Handler target;

    public Object obj;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "what=" + what +
                ", obj=" + obj +
                '}';
    }
}
