package com.mb.mubai.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @author: lzw
 * //
 * @date: 2017/6/23 下午4:14
 * //
 * @desc:
 */

public class LoginActivity {

    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        FormBody body = new FormBody.Builder()
                .add("username", "apptest")
                .add("password", "123456")
                .add("clientType", "android")
                .add("version", "2.2.0")
                .build();

        Request request = new Request.Builder()
                .url("http://www.zzscut.com//tornado/outLogin/applogin.do?username=apptest&password=123456&clientType=android&version=2.2.0")
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();

        System.out.print(response.body().string());

    }
}
