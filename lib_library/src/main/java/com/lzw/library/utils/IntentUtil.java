package com.lzw.library.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class IntentUtil {

    public static final int INTENT_REQUEST_CODE_ALBUM = 1001;
    public static final int INTENT_REQUEST_CODE_CAMERA = 1002;
    public static final int INTENT_REQUEST_CODE_CROP = 1003;
    public static final int INTENT_REQUEST_CODE_GRAFFITI = 1004;

    private static final String[] HTML = new String[]{".htm", ".html",
            ".php", ".jsp"};

    private static final String[] IMAGE = new String[]{".png", ".gif",
            ".jpg", ".jpeg", ".bmp"};

    private static final String[] AUDIO = new String[]{".mp3", ".wav",
            ".ogg", ".midi"};

    private static final String[] VIDEO = new String[]{".mp4", ".rmvb",
            ".avi", ".flv"};

    private static final String[] APK = new String[]{".apk"};

    private static final String[] TEXT = new String[]{".txt", ".java", ".c",
            ".cpp", ".py", ".xml", ".json", ".log"};

    private static final String[] CHM = new String[]{".chm"};

    private static final String[] WORD = new String[]{".doc", ".docx"};

    private static final String[] EXCEL = new String[]{".xls", ".xlsx"};

    private static final String[] PPT = new String[]{".ppt", ".pptx"};

    private static final String[] PDF = new String[]{".pdf"};

    /**
     * 跳转到相册
     *
     * @param activity
     * @return
     */
    public static boolean intentToAlbum(Activity activity) {
        Intent it = new Intent(Intent.ACTION_PICK, null);
        it.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        activity.startActivityForResult(it, INTENT_REQUEST_CODE_ALBUM);
        return true;
    }

    /**
     * 跳转到相机
     *
     * @param activity
     * @param savePath
     * @param saveName
     * @return
     */
    public static boolean intentToCamera(Activity activity, Context context,
                                         String savePath, String saveName) {
        if (StringUtil.isEmpty(savePath) || StringUtil.isEmpty(saveName)) {
            return false;
        }
        File dirFile = FileUtils.createDirFile(context, savePath);
        if (dirFile == null) {
            return false;
        }
        File file = FileUtils.createNewFile(context, savePath + saveName);
        if (file == null) {
            return false;
        }
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        activity.startActivityForResult(it, INTENT_REQUEST_CODE_CAMERA);
        return true;
    }

    /**
     * 跳转到浏览器
     *
     * @param context
     * @param uri     <li>http://www.google.com</li> <li>https://www.google.com</li>
     * @return
     */
    public static boolean intentToBrowser(Context context, String uri) {
        return intentToView(context, uri);
    }

    /**
     * 跳转到地图
     *
     * @param context
     * @param uri     <li>geo:latitude,longitude</li> <br>
     *                <li>geo:latitude,longitude?z=zoom</li><br>
     *                <li>geo:0,0?q=my+street+address</li><br>
     *                <li>geo:0,0?q=business+near+city</li><br>
     *                <li>
     *                google.streetview:cbll=lat,lng&cbp=1,yaw,,pitch,zoom&mz=
     *                mapZoom</li><br>
     * @return
     */
    public static boolean intentToMap(Context context, String uri) {
        return intentToView(context, uri);
    }

    /**
     * 跳转到View组件
     *
     * @param context
     * @param uri
     * @return
     */
    public static boolean intentToView(Context context, String uri) {
        if (context == null || StringUtil.isEmpty(uri)) {
            return false;
        }
        Uri u = Uri.parse(uri);
        Intent it = new Intent(Intent.ACTION_VIEW, u);
        context.startActivity(it);
        return true;
    }

    /**
     * 跳转到打电话界面
     *
     * @param context
     * @param phoneNumber
     * @return
     */
    public static boolean intentToDIAL(Context context, String phoneNumber) {
        if (context == null || StringUtil.isEmpty(phoneNumber)) {
            return false;
        }
        Uri u = Uri.parse("tel:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_DIAL, u);
        context.startActivity(it);
        return true;
    }

    /**
     * 直接拨打电话
     *
     * @param context
     * @param phoneNumber
     * @return
     */
    public static boolean intentToCall(Context context, String phoneNumber) {
        if (context == null || StringUtil.isEmpty(phoneNumber)) {
            return false;
        }
        Uri u = Uri.parse("tel:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_CALL, u);
        context.startActivity(it);
        return true;
    }

    /**
     * 调用发送短信界面
     *
     * @param context
     * @param phoneNumber
     * @param smsBody
     * @return
     */
    public static boolean intentToSMS(Context context, String phoneNumber,
                                      String smsBody) {
        if (context == null || StringUtil.isEmpty(phoneNumber)) {
            return false;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        if (!StringUtil.isEmpty(smsBody)) {
            it.putExtra("sms_body", smsBody);
        }
        context.startActivity(it);
        return true;
    }

    /**
     * 调用发送邮件界面
     *
     * @param context
     * @param emailAddress
     * @param emailBody
     * @return
     */
    public static boolean intentToEmail(Context context, String emailAddress,
                                        String emailBody) {
        if (context == null || StringUtil.isEmpty(emailAddress)) {
            return false;
        }
        Intent it = new Intent(Intent.ACTION_SEND);
        it.setType("plain/text");
        it.putExtra(Intent.EXTRA_EMAIL,
                new String[]{emailAddress});
        it.putExtra(Intent.EXTRA_SUBJECT, "");
        if (StringUtil.isEmpty(emailBody)) {
            it.putExtra(Intent.EXTRA_TEXT, "");
        } else {
            it.putExtra(Intent.EXTRA_TEXT, emailBody);
        }
        context.startActivity(Intent.createChooser(it, "请选择邮件发送软件"));
        return true;
    }

    /**
     * 查询一个应用在应用市场的内容
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean intentToSearchMarket(Context context,
                                               String packageName) {
        if (context == null || StringUtil.isEmpty(packageName)) {
            return false;
        }
        String str = "market://search?q=pname:" + packageName;
        try {
            Intent it = new Intent(Intent.ACTION_VIEW);
            it.setData(Uri.parse(str));
            context.startActivity(it);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 打开一个应用在应用市场的内容
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean intentToDetailMarket(Context context,
                                               String packageName) {
        if (context == null || StringUtil.isEmpty(packageName)) {
            return false;
        }
        String str = "market://details?id=" + packageName;
        try {
            Intent it = new Intent(Intent.ACTION_VIEW);
            it.setData(Uri.parse(str));
            context.startActivity(it);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 打开应用程序
     *
     * @param context
     * @param packageName 包名
     * @param className   class名(需加入包名,完整的名字)
     * @return
     */
    public static boolean intentToOpenApplication(Context context,
                                                  String packageName, String className) {
        if (context == null || StringUtil.isEmpty(packageName)
                || StringUtil.isEmpty(className)) {
            return false;
        }
        Intent it = new Intent(Intent.ACTION_MAIN, null);
        it.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName(packageName, className);
        it.setComponent(cn);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
        return true;
    }

    /**
     * 跳转到桌面
     *
     * @param context
     * @return
     */
    public static boolean intentToHome(Context context) {
        if (context == null) {
            return false;
        }
        Intent it = new Intent(Intent.ACTION_MAIN);
        it.addCategory(Intent.CATEGORY_HOME);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(it);
        return true;
    }

    /**
     * 打开HTML文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenHtmlFile(Context context, File file) {
        if (!checkFileEndings(file, HTML)) {
            return false;
        }
        Uri u = Uri.parse(file.toString()).buildUpon()
                .encodedAuthority("com.android.htmlfileprovider")
                .scheme("content").encodedPath(file.toString()).build();
        Intent it = new Intent("android.intent.action.VIEW");
        it.setDataAndType(u, "text/html");
        context.startActivity(it);
        return true;
    }

    /**
     * 打开图片文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenImageFile(Context context, File file) {
        if (!checkFileEndings(file, IMAGE)) {
            return false;
        }
        Intent it = new Intent("android.intent.action.VIEW");
        it.addCategory("android.intent.category.DEFAULT");
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "image/*");
        context.startActivity(it);
        return true;
    }

    /**
     * 打开PDF文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenPdfFile(Context context, File file) {
        if (!checkFileEndings(file, PDF)) {
            return false;
        }
        Intent it = new Intent("android.intent.action.VIEW");
        it.addCategory("android.intent.category.DEFAULT");
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "application/pdf");
        context.startActivity(it);
        return true;
    }

    /**
     * 打开文本文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenTextFile(Context context, File file) {
        if (!checkFileEndings(file, TEXT)) {
            return false;
        }
        Intent it = new Intent("android.intent.action.VIEW");
        it.addCategory("android.intent.category.DEFAULT");
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "text/plain");
        context.startActivity(it);
        return true;
    }

    /**
     * 打开音频文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenAudioFile(Context context, File file) {
        if (!checkFileEndings(file, AUDIO)) {
            return false;
        }
        Intent it = new Intent("android.intent.action.VIEW");
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        it.putExtra("oneshot", 0);
        it.putExtra("configchange", 0);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "audio/*");
        context.startActivity(it);

        return true;
    }

    /**
     * 打开视频文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenVideoFile(Context context, File file) {
        if (!checkFileEndings(file, VIDEO)) {
            return false;
        }
        Intent it = new Intent("android.intent.action.VIEW");
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        it.putExtra("oneshot", 0);
        it.putExtra("configchange", 0);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "video/*");
        context.startActivity(it);
        return true;
    }

    /**
     * 打开CHM文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenChmFile(Context context, File file) {
        if (!checkFileEndings(file, CHM)) {
            return false;
        }
        Intent it = new Intent("android.intent.action.VIEW");
        it.addCategory("android.intent.category.DEFAULT");
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "application/x-chm");
        context.startActivity(it);
        return true;
    }

    /**
     * 打开Word文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenWordFile(Context context, File file) {
        if (!checkFileEndings(file, WORD)) {
            return false;
        }
        Intent it = new Intent("android.intent.action.VIEW");
        it.addCategory("android.intent.category.DEFAULT");
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "application/msword");
        context.startActivity(it);
        return true;
    }

    /**
     * 打开Excel文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenExcelFile(Context context, File file) {
        if (!checkFileEndings(file, EXCEL)) {
            return false;
        }
        Intent it = new Intent("android.intent.action.VIEW");
        it.addCategory("android.intent.category.DEFAULT");
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "application/vnd.ms-excel");
        context.startActivity(it);
        return true;
    }

    /**
     * 打开PPT文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenPPTFile(Context context, File file) {
        if (!checkFileEndings(file, PPT)) {
            return false;
        }
        Intent it = new Intent("android.intent.action.VIEW");
        it.addCategory("android.intent.category.DEFAULT");
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "application/vnd.ms-powerpoint");
        context.startActivity(it);
        return true;
    }

    /**
     * 打开APK文件
     *
     * @param context
     * @param file
     * @return
     */
    public static boolean OpenApkFile(Context context, File file) {
        if (!checkFileEndings(file, APK)) {
            return false;
        }
        Intent it = new Intent();
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        it.setAction(Intent.ACTION_VIEW);
        Uri u = Uri.fromFile(file);
        it.setDataAndType(u, "application/vnd.android.package-archive");
        context.startActivity(it);
        return true;
    }

    /**
     * 检查文件后缀
     *
     * @param file
     * @param fileEndings
     * @return
     */
    private static boolean checkFileEndings(File file, String... fileEndings) {
        if (file == null || ArrayUtil.isEmpty(fileEndings) || !file.exists()) {
            return false;
        }
        for (String s : fileEndings) {
            if (file.getPath().toLowerCase().endsWith(s)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取mac地址
     */
    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";

    public static String getAddressMAC(Context context) {
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInf = wifiMan.getConnectionInfo();

        if (wifiInf != null && marshmallowMacAddress.equals(wifiInf.getMacAddress())) {
            String result = null;
            try {
                result = getAdressMacByInterface();
                if (result != null) {
                    return result;
                } else {
                    result = getAddressMacByFile(wifiMan);
                    return result;
                }
            } catch (IOException e) {
                Log.e("MobileAccess", "Erreur lecture propriete Adresse MAC");
            } catch (Exception e) {
                Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
            }
        } else {
            if (wifiInf != null && wifiInf.getMacAddress() != null) {
                return wifiInf.getMacAddress();
            } else {
                return "";
            }
        }
        return marshmallowMacAddress;
    }

    private static String getAdressMacByInterface() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }

                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:", b));
                    }

                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }

        } catch (Exception e) {
            Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
        }
        return null;
    }

    private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
        String ret;
        int wifiState = wifiMan.getWifiState();
        wifiMan.setWifiEnabled(true);
        File fl = new File(fileAddressMac);
        FileInputStream fin = new FileInputStream(fl);
        ret = crunchifyGetStringFromStream(fin);
        fin.close();
        boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
        wifiMan.setWifiEnabled(enabled);
        return ret;
    }

    private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
        if (crunchifyStream != null) {
            Writer crunchifyWriter = new StringWriter();

            char[] crunchifyBuffer = new char[2048];
            try {
                Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
                int counter;
                while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
                    crunchifyWriter.write(crunchifyBuffer, 0, counter);
                }
            } finally {
                crunchifyStream.close();
            }
            return crunchifyWriter.toString();
        } else {
            return "No Contents";
        }
    }
}
