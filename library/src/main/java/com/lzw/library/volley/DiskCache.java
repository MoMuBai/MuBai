package com.lzw.library.volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.toolbox.ImageLoader;
import com.lzw.library.helper.CloseHelper;
import com.lzw.library.utils.MD5Util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
public class DiskCache implements ImageLoader.ImageCache {

        static final String cacheDir = "/sdcard/Download/";
//    static String cacheDir = SDCardHelper.getSdCardPath();

        @Override
        public Bitmap getBitmap(String url) {
                url = MD5Util.getMD5Str(url);
                return BitmapFactory.decodeFile(cacheDir + url + ".jpg");
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
                url = MD5Util.getMD5Str(url);
                File imageFile = new File(cacheDir, url + ".jpg");
                if (!imageFile.exists()) {
                        FileOutputStream fileOutputStream = null;
                        try {
                                imageFile.createNewFile();
                                fileOutputStream = new FileOutputStream(imageFile);
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                        } catch (IOException e) {
                                e.printStackTrace();
                        } finally {
                                if (fileOutputStream != null) {
                                        CloseHelper.close(fileOutputStream);
                                }
                        }
                }
        }
}
