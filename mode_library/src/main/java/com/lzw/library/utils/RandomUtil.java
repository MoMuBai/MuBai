package com.lzw.library.utils;

import java.util.Random;

/**
 * RandomUtil
 * 
 * @author wei2bei132
 * 
 */
public class RandomUtil {
	/**
	 * 数字和字母
	 */
	public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 数字
	 */
	public static final String NUMBERS = "0123456789";
	/**
	 * 字母
	 */
	public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 大写字母
	 */
	public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 小写字母
	 */
	public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 从数字和字母中随机获取固定个数字符进行拼接
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumbersAndLetters(int length) {
		return getRandom(NUMBERS_AND_LETTERS, length);
	}

	/**
	 * 从数字中随机获取固定个数字符进行拼接
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomNumbers(int length) {
		return getRandom(NUMBERS, length);
	}

	/**
	 * 从字母中随机获取固定个数字符进行拼接
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomLetters(int length) {
		return getRandom(LETTERS, length);
	}

	/**
	 * 从大写字母中随机获取固定个数字符进行拼接
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomCapitalLetters(int length) {
		return getRandom(CAPITAL_LETTERS, length);
	}

	/**
	 * 从小写字母中随机获取固定个数字符进行拼接
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomLowerCaseLetters(int length) {
		return getRandom(LOWER_CASE_LETTERS, length);
	}

	/**
	 * 从字符串中随机获取固定个数字符进行拼接
	 * 
	 * @param source
	 * @param length
	 * @return
	 */
	public static String getRandom(String source, int length) {
		return StringUtil.isEmpty(source) ? null : getRandom(
				source.toCharArray(), length);
	}

	/**
	 * 从字符数组中随机获取固定个数字符进行拼接
	 * 
	 * @param sourceChar
	 * @param length
	 * @return
	 */
	public static String getRandom(char[] sourceChar, int length) {
		if (sourceChar == null || sourceChar.length == 0 || length < 0) {
			return null;
		}

		StringBuilder str = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			str.append(sourceChar[random.nextInt(sourceChar.length)]);
		}
		return str.toString();
	}
}
