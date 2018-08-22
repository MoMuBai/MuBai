package com.lzw.library.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lzw on 2016/11/10.
 */

public class PhotoUtil {


    /**
     * 获取系统相册
     *
     * @param activity
     * @param requestCode
     */
    public static void getPhoto(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }


    /**
     * 调用系统拍摄
     *
     * @param activity
     * @param requestCode
     */
    public static void takePhoto(Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT == 24) {//当手机系统为7.0(API:24)时需要通过FileProvider获取Uri
            File file = new File(Environment.getExternalStorageDirectory(), "/files/" + System.currentTimeMillis() + ".jpg");
            if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
            Uri imageUri = FileProvider.getUriForFile(activity, "com.mob.lzg.fileprovider", file);//通过FileProvider创建一个content类型的Uri
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
            activity.startActivityForResult(intent, requestCode);
        } else if (false) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            activity.startActivityForResult(intent, requestCode);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
            Uri imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            activity.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 拍照打开照相机！
     *
     * @param requestcode 返回值
     * @param activity    上下文
     * @param fileName    生成的图片文件的路径
     */
    public static void toTakePhoto(int requestcode, Activity activity, String fileName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("camerasensortype", 2);// 调用前置摄像头
        intent.putExtra("autofocus", true);// 自动对焦
        intent.putExtra("fullScreen", false);// 全屏
        intent.putExtra("showActionIcons", false);
        try {//创建一个当前任务id的文件然后里面存放任务的照片的和路径！这主文件的名字是用uuid到时候在用任务id去查路径！
            File file = new File(fileName);
            if (!file.exists()) {//如果这个文件不存在就创建一个文件夹！
                file.mkdirs();
            }
            Uri uri = Uri.fromFile(new File(fileName));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(intent, requestcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 相机权限6.0
     *
     * @param activity
     * @param permission
     * @param requestCode
     */
    public static boolean setPermission(Activity activity, String permission, int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(activity, permission);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * 根据Uri返回Bitmap对象
     *
     * @param activity
     * @param uri
     */
    public static Bitmap getBitmapFromUri(Activity activity, Uri uri) {
        // 读取uri所在的图片
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), uri);
        } catch (IOException e) {
            L.d("FileImg", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }


    /**
     * 根据Bitmap返回String
     *
     * @param activity
     * @param bitmap
     */
    public static String getStringFromBitmap(Activity activity, Bitmap bitmap) {
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, null, null));
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.managedQuery(uri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String img_path = cursor.getString(column_index);
        return img_path;
    }


    /**
     * 根据Bitmap获取Uri
     *
     * @param bm
     */
    private Uri saveBitmap(Bitmap bm) {
        File tmdir = new File(Environment.getExternalStorageDirectory()
                + "/com.test");
        if (!tmdir.exists()) {
            tmdir.mkdir();
        }
        File img = new File(tmdir.getAbsolutePath() + "ac.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fos);
            fos.flush();
            fos.close();
            return Uri.fromFile(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 调用裁剪功能
     *
     * @param uri
     */
    private void startImageZoom(Activity activity, Uri uri) {
        // 调用系统裁剪功能
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, 3);
    }
}
