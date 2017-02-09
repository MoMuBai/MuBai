package com.mb.mubai.base.helper;

import android.text.TextUtils;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/11
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  NoEmptyHelper
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class NoEmptyHelper {
        private static NoEmptyHelper noEmptyHelper;

        private NoEmptyHelper() {
        }

        public static NoEmptyHelper getNoEmptyHelper() {
                if (null == noEmptyHelper) {
                        noEmptyHelper = new NoEmptyHelper();
                }
                return noEmptyHelper;
        }


        /**
         * 对象不为Null
         */
        public boolean noEmpty(Object o, Object... args) {
                if (null != o) {
                        if (args.length > 0) {
                                for (int i = 0, size = args.length; i < size; i++) {
                                        if (null != args[i]) {
                                                return true;
                                        } else {
                                                return false;
                                        }
                                }
                        }
                        return true;
                } else {
                        return false;
                }
        }


        /**
         * CharSequence不为Null && length大于0
         */
        public boolean noEmpty(CharSequence str, CharSequence... args) {
                if (!TextUtils.isEmpty(str) && str.length() > 0) {
                        if (args.length > 0) {
                                for (int i = 0, size = args.length; i < size; i++) {
                                        if (!TextUtils.isEmpty(args[i]) && args[i].length() > 0) {
                                                return true;
                                        } else {
                                                return false;
                                        }
                                }
                        }
                        return true;
                } else {
                        return false;
                }
        }

        /**
         * int不为0
         */
        public boolean noEmptyInt(int i, int... args) {
                if (0 != i) {
                        if (args.length > 0) {
                                for (int j = 0, size = args.length; j < size; j++) {
                                        if (0 != args[j]) {
                                                return true;
                                        } else {
                                                return false;
                                        }
                                }
                        }
                        return true;
                } else {
                        return false;
                }
        }
}
