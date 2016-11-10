package com.mob.mubai.base.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 *reated by yifuhua on 16/4/25.
 */
public class CloseHelper {
    public static final void close(Closeable closeable) {
        try {
            closeable.close();
            closeable = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
