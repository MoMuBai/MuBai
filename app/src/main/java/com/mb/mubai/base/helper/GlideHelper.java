package com.mb.mubai.base.helper;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mb.mubai.App;
import com.mb.mubai.R;

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
 * //        ┃　　　┃   代码无BUG！      @Desc  GlideHelper
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class GlideHelper {
        private static GlideHelper glideHelper;

        private Glide glide;

        private GlideHelper() {
                glide = Glide.get(App.getInstance());
        }

        public static GlideHelper getGlideHelper() {
                if (null == glideHelper) {
                        glideHelper = new GlideHelper();
                }
                return glideHelper;
        }


        /**
         * Glide加载Img 不需要加载图片和错误图片
         *
         * @param imgUrl
         * @param view
         */
        public void loadImg(ImageView view, String imgUrl) {
                glide.with(App.getInstance())
                          .load(imgUrl)
                          .into(view);
        }


        /**
         * Glide加载Img
         *
         * @param imgUrl
         * @param view
         */
        public void loadImg(String imgUrl, ImageView view) {
                glide.with(App.getInstance())
                          .load(imgUrl)
                          .placeholder(R.drawable.mubai)
                          .error(R.drawable.mubai)
                          .into(view);
        }

        /**
         * Glide加载Img需要指定加载图片和错误图片
         *
         * @param imgUrl
         * @param loadDrawable
         * @param errorDrawable
         * @param view
         */
        public void loadImg(String imgUrl, Drawable loadDrawable, Drawable errorDrawable, ImageView view) {
                glide.with(App.getInstance())
                          .load(imgUrl)
                          .placeholder(loadDrawable)
                          .error(errorDrawable)
                          .into(view);
        }
}
