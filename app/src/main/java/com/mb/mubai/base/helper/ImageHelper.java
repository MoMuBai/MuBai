package com.mb.mubai.base.helper;

import android.widget.ImageView;

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
 * //        ┃　　　┃   代码无BUG！      @Desc  ImageViewHelper
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class ImageHelper {
        private static ImageHelper imageHelper;

        private ImageHelper() {
        }

        public static ImageHelper getImageHelper() {
                if (null == imageHelper) {
                        imageHelper = new ImageHelper();
                }
                return imageHelper;
        }

        /**
         * 设置Url到ImageView
         *
         * @param url
         * @param imageView
         */
        public void setImg(String url, ImageView imageView) {
                if (NoEmptyHelper.getNoEmptyHelper().noEmpty(url, imageView)) {
                        GlideHelper.getGlideHelper().loadImg(url, imageView);
                }
        }
}
