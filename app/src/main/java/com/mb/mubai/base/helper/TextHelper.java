package com.mb.mubai.base.helper;

import android.widget.TextView;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/10
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class TextHelper {
        private static TextHelper textHelper;

        private TextHelper() {
        }

        public static TextHelper getTextHelper() {
                if (null == textHelper) {
                        textHelper = new TextHelper();
                }
                return textHelper;
        }


        /**
         * 设置data到TextView
         *
         * @param textView
         * @param data
         */
        public void setText(TextView textView, CharSequence data) {
                if (NoEmptyHelper.getNoEmptyHelper().noEmpty(data)) {
                        textView.setText(data);
                }
        }

        /**
         * 设置resId到TextView
         *
         * @param textView
         * @param resId
         */
        public void setText(TextView textView, int resId) {
                if (NoEmptyHelper.getNoEmptyHelper().noEmptyInt(resId)) {
                        textView.setText(resId);
                }
        }
}
