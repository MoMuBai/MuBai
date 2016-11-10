package com.mob.mubai.base.utils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mubai on 2016/11/8.
 * MD5加密
 *
 */
public class MD5Util {

    /**
     * MD5加密
     * @param str
     *
     * */
    public static String getMD5Str(String str) {
        str = str.replace(" ", "+");//PHP的空格是转换为+
        MessageDigest messageDigest = null;// MD5转换器
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest(); // 输入的字符串转换成字节数组
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

    /**
     * MD5
     *
     * @param input 源数据
     * @return md5签名。签名失败时，返回null。
     */
    public static String md5(String input) {
        try {
            // 拿到一个MD5转换器
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 输入的字符串转换成字节数组
            byte[] inputByteArray = input.getBytes();
            // inputByteArray是输入字符串转换得到的字节数组
            messageDigest.update(inputByteArray);
            // 转换并返回结果，也是字节数组，包含16个元素
            byte[] resultByteArray = messageDigest.digest();
            // 字符数组转换成字符串返回
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * 将字节数组转化成16进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteArrayToHex(byte[] byteArray) {
        // 初始化字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        // new一个字符数组，用来组成结果字符串
        char[] resultCharArray = new char[byteArray.length * 2];
        // 遍历字节数组
        int index = 0;
        for (byte b : byteArray) {
            // 把一个byte切割成2位十六进制字符
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }

}
