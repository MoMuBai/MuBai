package com.lzw.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/8
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  防止连续弹出Toast
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public final class To {

        private static boolean isToast = true;

        private static long oneTime = 0;

        private static long twoTime = 0;

        private static String oldMsg;

        private static Context mContext;

        private static Toast mToast;


        private To() {
        /* cannot be instantiated */
                throw new UnsupportedOperationException("cannot be instantiated");
        }

        public static void init(Context context) {
                mContext = context;
        }


        public static void d(String msg) {
                if (isToast) {
                        if (mToast == null) {
                                mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
                                mToast.show();
                                oneTime = System.currentTimeMillis();
                        } else {
                                twoTime = System.currentTimeMillis();
                                if (msg.equals(oldMsg)) {
                                        if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                                                mToast.show();
                                        }
                                } else {
                                        oldMsg = msg;
                                        mToast.setText(msg);
                                        mToast.show();
                                }
                        }
                        oneTime = twoTime;
                }
        }
}
