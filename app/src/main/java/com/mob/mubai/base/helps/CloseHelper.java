package com.mob.mubai.base.helps;

import java.io.Closeable;
import java.io.IOException;

/**
 *Created by mubai on 2016/11/8.
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
