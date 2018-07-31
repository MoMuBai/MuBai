package com.lzw.mode_user.user;

import android.util.Log;

import java.util.regex.Pattern;

/**
 * Author: lzw
 * Date: 2018/6/15
 * Description: This is User
 */

public class User {
    protected static void main(String args[]) {
        String content = "hello world";
        String pattern = ".*hello.*";
        boolean isPattern = Pattern.matches(pattern, content);
        Log.d("UserActivity", "isPattern:" + isPattern);
    }
}
