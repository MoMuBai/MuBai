package com.mob.mubai.ui.activity;

import android.app.ActivityManager;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.hide.SystemProperties;
import com.mob.mubai.base.utils.L;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
        String className = "com.mob.mubai.base.hide.Worker";
        try {

            Class c_1 = Class.forName(className);
            Object o_1 = c_1.newInstance();
            L.d(TAG,"反射出无参的构造方法并得到对象:"+o_1.toString());

            Class c_2 = Class.forName(className);
            Constructor constructor_1 = c_2.getConstructor(int.class,String.class);
            Object o_2 = constructor_1.newInstance(23,"MuBai");
            L.d(TAG,"反射出带参数的构造方法并得到对象"+o_2.toString());


            Class c_3 = Class.forName(className);
            Field field_1 = c_3.getDeclaredField("age");
            Field field_2 = c_3.getField("id");
            L.d(TAG,"获取类的私有字段"+field_1);
            L.d(TAG,"获取类的公有字段"+field_2);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
