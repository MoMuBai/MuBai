package com.lzw.library.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;


import com.lzw.library.helper.CloseHelper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 本类由: Risky57 创建于: 16/3/23.
 */
public class StreamUtil {
    public static String inputToString(InputStream is) throws IOException {
        if (is != null) {
            InputStreamReader reader = new InputStreamReader(is);
            char[] data = new char[1024];
            int temp = 0;
            StringBuffer buffer = new StringBuffer();
            while ((temp = reader.read(data)) != -1) {
                String str = String.valueOf(data, 0, temp);
                buffer.append(str);
            }
            is.close();
            if (reader != null) reader.close();
            return buffer.toString();
        } else return null;
    }

    private static final String CACHE_PATH = Environment.getExternalStorageDirectory().getPath();

    public static Bitmap inputToBitmap(InputStream is, String fileName) throws IOException {
        File file = new File(CACHE_PATH, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[512];
        int temp = 0;
        while ((temp = is.read(buffer)) != -1) {
            fos.write(buffer, 0, temp);
        }
        CloseHelper.close(fos);
        CloseHelper.close(is);
        Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bmp;
    }

    public static void bmpToStream(Bitmap bmp, OutputStream os) {
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, os);
        CloseHelper.close(os);
    }

    public static boolean stringToStream(String content, OutputStream os) throws IOException {
        if (os == null || TextUtils.isEmpty(content)) {
            return false;
        }
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write(content);
        writer.flush();
        writer.close();
        return true;

    }

    public static int formatStream(InputStream is) throws IOException {
        byte[] buffer = new byte[2];
        is.read(buffer);
        return parseBytes(buffer);
    }

    public static final int FORMAT_PNG = 0x8950;
    public static final int FORMAT_JPEG = 0xffd8;

    private static int parseHex(String hex) {

        return Integer.parseInt(hex, 16);
    }

    private static int parseBytes(byte[] buffer) {
        String index0 = byteToHex(buffer[0]);
        String index1 = byteToHex(buffer[1]);
        String num = index0 + index1;
        return parseHex(num);

    }

    /**
     * byte 转为 16进制
     */
    public static String byteToHex(byte buffer) {
        int temp = buffer & 0xFF;
        return Integer.toHexString(temp);
    }

    /**
     * 16进制 转为 byte
     */
    public static byte hexToByte(String hex) {
        hex = hex.toUpperCase();
        char[] chars = hex.toCharArray();
        return (byte) (charToByte(chars[0]) << 4 | charToByte(chars[1]));
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
