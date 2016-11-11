package com.mob.mubai.ui.activity;

import android.app.ActivityManager;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mubai on 2016/11/11.
 * Android中Java反射技术的使用示例
 * 在Java中描述字节码文件(xxx.class)的类叫Class
 * 反射的过程可视为剖析Class的过程
 */

public class ReflectionActivity extends BaseActivity {
    @Override
    protected int getLayout() {
        return R.layout.activity_reflection;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(){
        getReflection();
    }

    private void getReflection() {
        String className = "";
        try {
            Class c = Class.forName(className);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
