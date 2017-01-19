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

        private static Context mContext;
        private static Toast mToast;

        private To() {
        /* cannot be instantiated */
                throw new UnsupportedOperationException("cannot be instantiated");
        }

        public static void init(Context context) {
                mContext = context;
                mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }

        public static boolean isShow = true;


        public static void showShort(CharSequence message) {
                if (isShow) {
                        mToast.setText(message);
                        mToast.show();
                }
        }


        public static void showLong(CharSequence message) {
                if (isShow) {
                        mToast.setDuration(Toast.LENGTH_LONG);
                        mToast.setText(message);
                        mToast.show();
                }
        }


}
