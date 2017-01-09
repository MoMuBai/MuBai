package com.mob.mubai.ui.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.ImageUtil;
import com.mob.mubai.base.utils.L;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
 * //      ┗━┓　　　┏━━━┛               @Date  2016/11/11
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  Android中Java反射技术的使用示例  在Java中描述字节码文件(xxx.class)的类叫Class
 * //        ┃　　　┗━━━━━━━━━┓                反射的过程可视为剖析Class的过程
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */

public class ReflectionActivity extends BaseActivity {
        @Bind(R.id.tv_1)
        TextView tv1;
        @Bind(R.id.tv_2)
        TextView tv2;
        @Bind(R.id.tv_3)
        TextView tv3;
        @Bind(R.id.tv_4)
        TextView tv4;
        @Bind(R.id.tv_5)
        TextView tv5;

        @Override
        protected int getLayout() {
                return R.layout.activity_reflection;
        }

        @Override
        protected void initView() {
        }

        @Override
        protected void initData() {
                getReflection();
        }

        private void getReflection() {
                String className = "com.mob.mubai.data.bean";
                try {

                        Class c_1 = Class.forName(className);
                        Object o_1 = c_1.newInstance();
                        L.d(TAG, "反射出无参的构造方法并得到对象:" + o_1.toString());

                        Class c_2 = Class.forName(className);
                        Constructor constructor_1 = c_2.getConstructor(int.class, String.class);
                        Object o_2 = constructor_1.newInstance(23, "MuBai");
                        L.d(TAG, "反射出带参数的构造方法并得到对象" + o_2.toString());


                        Class c_3 = Class.forName(className);
                        Field field_1 = c_3.getDeclaredField("age");
                        Field field_2 = c_3.getField("id");
                        L.d(TAG, "获取类的私有字段" + field_1);
                        L.d(TAG, "获取类的公有字段" + field_2);

                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                } catch (InstantiationException e) {
                        e.printStackTrace();
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                } catch (InvocationTargetException e) {
                        e.printStackTrace();
                } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                }
        }


        @OnClick({R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5})
        void click(View view) {
                if (view.getId() == R.id.tv_1) {//Reflection压缩
                        Drawable drawable = getResources().getDrawable(R.drawable.mubai);
                        Bitmap bitmap = ImageUtil.drawableToBitmap(drawable);
                        L.d(TAG + "11", "压缩前图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                                  + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
                        bitmap = ImageUtil.createReflectionImageWithOrigin(bitmap);
                        L.d(TAG + "11", "压缩前图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                                  + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
                } else if (view.getId() == R.id.tv_2) {//质量压缩
                        Drawable drawable = getResources().getDrawable(R.drawable.mubai);
                        Bitmap bitmap = ImageUtil.drawableToBitmap(drawable);
                        L.d(TAG + "11", "压缩前图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                                  + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
                        bitmap = ImageUtil.compressImage(bitmap);
                        L.d(TAG + "11", "压缩前图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                                  + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
                } else if (view.getId() == R.id.tv_3) {//比例压缩
                        Drawable drawable = getResources().getDrawable(R.drawable.mubai);
                        Bitmap bitmap = ImageUtil.drawableToBitmap(drawable);
                        L.d(TAG + "11", "压缩前图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                                  + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
                        bitmap = ImageUtil.comp(bitmap);
                        L.d(TAG + "11", "压缩前图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                                  + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
                } else if (view.getId() == R.id.tv_4) {//圆角
                        Drawable drawable = getResources().getDrawable(R.drawable.mubai);
                        Bitmap bitmap = ImageUtil.drawableToBitmap(drawable);
                        L.d(TAG + "11", "压缩前图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                                  + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
                        bitmap = ImageUtil.getRoundedCornerBitmap(bitmap, 20f);
                        L.d(TAG + "11", "压缩前图片的大小" + (bitmap.getByteCount() / 1024 / 1024)
                                  + "M宽度为" + bitmap.getWidth() + "高度为" + bitmap.getHeight());
                }
        }
}
