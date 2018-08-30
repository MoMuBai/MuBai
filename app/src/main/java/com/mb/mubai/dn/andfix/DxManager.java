package com.mb.mubai.dn.andfix;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import dalvik.system.DexFile;

/**
 * Author: lzw
 * Date: 2018/8/23
 * Description: This is DxManager
 */

public class DxManager {
    private Context context;

    private String TAG = "LZW";

    public DxManager(Context context) {
        this.context = context;
    }

    public void loadDex(File dexFilePatch) {
        File optFile = new File(context.getCacheDir(), dexFilePatch.getName());
        if (optFile.exists()) {
            optFile.delete();
        }
        try {
            /**
             * 加载dex文件
             */
            DexFile dexFile = DexFile.loadDex(dexFilePatch.getAbsolutePath(), optFile.getAbsolutePath(), Context.MODE_PRIVATE);
            /**
             * 遍历dex文件里面的class
             */
            Enumeration<String> entry = dexFile.entries();//得到class集合
            while (entry.hasMoreElements()) {
                String className = entry.nextElement();//得到修复好的Class类名
                /**
                 * 修复好的Class，怎么样找到出bug的Class？
                 */
                Class realClazz = dexFile.loadClass(className, context.getClassLoader());//找到具体的class
                Log.d(TAG, className);
                /**
                 * 修复方法
                 */
                fix(realClazz);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fix(Class realClazz) {
        Method[] methods = realClazz.getDeclaredMethods();
        for (Method method : methods) {
            /**
             * 拿到注解
             */
            ReplaceClass replaceClass = method.getAnnotation(ReplaceClass.class);
            if (replaceClass == null) {
                continue;
            }
            /**
             * 出错的类名和方法名
             */
            String wrongClazzName = replaceClass.clazz();
            String wrongMethodName = replaceClass.method();
            /**
             * 找到具体出错的class
             */
            try {
                Class wrongClass = Class.forName(wrongClazzName);
                /**
                 * 找到具体出错的方法
                 */
                Method wrongMethod = wrongClass.getMethod(wrongMethodName, method.getParameterTypes());
                replace(wrongMethod, method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void replace(Method wrongMethod, Method method) {

    }
}
