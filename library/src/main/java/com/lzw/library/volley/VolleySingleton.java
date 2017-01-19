package com.lzw.library.volley;

import android.content.Context;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/19
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
public class VolleySingleton {
        private static final String cacheDir = DiskCache.cacheDir;
        private static Context sContext;

        private RequestQueue queue;
        private ImageLoader imageLoader;

        private VolleySingleton() {
                File file = new File(cacheDir);
                if (!file.exists()) {
                        file.mkdir();
                }
                queue = getQueue();
                imageLoader = new ImageLoader(queue, new DoubleCache());
        }

        public static void init(Context context) {
                sContext = context.getApplicationContext();
        }

        private static final class SingletonHolder {
                private static final VolleySingleton instance = new VolleySingleton();
        }

        public static VolleySingleton getInstance() {
                return SingletonHolder.instance;
        }

        private RequestQueue getQueue() {
                if (queue == null) {
                        queue = Volley.newRequestQueue(sContext);
                }
                return queue;
        }

        public static final String TAG = "VolleySingleton";

        public <T> void _addRequest(Request<T> request) {
                request.setTag(TAG);
                queue.add(request);
        }

        public <T> void _addRequest(Request<T> request, Object tag) {
                request.setTag(tag);
                queue.add(request);
        }

        public void _addRequest(String url, Response.Listener<String> listener,
                                Response.ErrorListener error) {
                StringRequest stringRequest = new StringRequest(url, listener, error);
                _addRequest(stringRequest);
        }

        public <T> void _addRequest(String url, Class<T> clazz, Response.Listener<T> success, Response.ErrorListener failed) {
                GsonRequest<T> request = new GsonRequest<T>(url, clazz, success, failed);
                _addRequest(request);
        }

        public void removeRequest(Object tag) {
                queue.cancelAll(tag);
        }

        public ImageLoader _getImageLoader() {
                return imageLoader;
        }

        public static void addRequest(String url, Response.Listener<String> listener,
                                      Response.ErrorListener error) {
                getInstance()._addRequest(url, listener, error);
        }

        public static <T> void addRequest(String url, Class<T> clazz, Response.Listener<T> success, Response.ErrorListener failed) {
                getInstance()._addRequest(url, clazz, success, failed);
        }

        public static ImageLoader getImageLoader() {
                return getInstance()._getImageLoader();
        }

        public static void loadImage(String url, ImageView img, int defaultImg, int errorImg) {
                ImageLoader.ImageListener listener = ImageLoader.getImageListener(img, defaultImg, errorImg);
                getImageLoader().get(url, listener);
        }
}
