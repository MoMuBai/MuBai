package com.lzw.library.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ListUtil
 * 
 * @author wei2bei132
 * 
 */
public class ListUtil {
	/**
	 * 默认拼接间隔符号
	 */
	public static final String DEFAULT_JOIN_SEPARATOR = ",";

	/**
	 * 判断是否为空
	 * 
	 * @param sourceList
	 * @return
	 */
	public static <V> boolean isEmpty(List<V> sourceList) {
		return (sourceList == null || sourceList.size() == 0);
	}

	/**
	 * 判断是否相等
	 * 
	 * @param actual
	 * @param expected
	 * @return
	 */
	public static <V> boolean isEquals(ArrayList<V> actual,
			ArrayList<V> expected) {
		if (actual == null) {
			return expected == null;
		}
		if (expected == null) {
			return false;
		}
		if (actual.size() != expected.size()) {
			return false;
		}

		for (int i = 0; i < actual.size(); i++) {
			if (!ObjectUtil.isEquals(actual.get(i), expected.get(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 默认间隔符号的拼接
	 * 
	 * @param list
	 * @return
	 */
	public static String join(List<String> list) {
		return join(list, DEFAULT_JOIN_SEPARATOR);
	}

	/**
	 * 自定义char符号的拼接
	 * 
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String join(List<String> list, char separator) {
		return join(list, new String(new char[] { separator }));
	}

	/**
	 * 自定义String符号的拼接
	 * 
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String join(List<String> list, String separator) {
		if (isEmpty(list)) {
			return "";
		}
		if (separator == null) {
			separator = DEFAULT_JOIN_SEPARATOR;
		}

		StringBuilder joinStr = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			joinStr.append(list.get(i));
			if (i != list.size() - 1) {
				joinStr.append(separator);
			}
		}
		return joinStr.toString();
	}

	/**
	 * 添加不同的实体
	 * 
	 * @param sourceList
	 * @param entry
	 * @return
	 */
	public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
		return (sourceList != null && !sourceList.contains(entry)) ? sourceList
				.add(entry) : false;
	}

	/**
	 * 从List中添加不同的实体
	 * 
	 * @param sourceList
	 * @param entryList
	 * @return
	 */
	public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
		if (sourceList == null || isEmpty(entryList)) {
			return 0;
		}

		int sourceCount = sourceList.size();
		for (V entry : entryList) {
			if (!sourceList.contains(entry)) {
				sourceList.add(entry);
			}
		}
		return sourceList.size() - sourceCount;
	}

	/**
	 * 删除相同的实体
	 * 
	 * @param sourceList
	 * @return
	 */
	public static <V> int distinctList(List<V> sourceList) {
		if (isEmpty(sourceList)) {
			return 0;
		}

		int sourceCount = sourceList.size();
		int sourceListSize = sourceList.size();
		for (int i = 0; i < sourceListSize; i++) {
			for (int j = (i + 1); j < sourceListSize; j++) {
				if (sourceList.get(i).equals(sourceList.get(j))) {
					sourceList.remove(j);
					sourceListSize = sourceList.size();
					j--;
				}
			}
		}
		return sourceCount - sourceList.size();
	}

	/**
	 * 添加不为null的实体
	 * 
	 * @param sourceList
	 * @param value
	 * @return
	 */
	public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
		return (sourceList != null && value != null) ? sourceList.add(value)
				: false;
	}

	/**
	 * 得到某个实体的上一个实体
	 * 
	 * @param sourceList
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <V> V getLast(List<V> sourceList, V value) {
		return (sourceList == null) ? null : (V) ArrayUtil.getLast(
				sourceList.toArray(), value, true);
	}

	/**
	 * 得到某个实体的下一个实体
	 * 
	 * @param sourceList
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <V> V getNext(List<V> sourceList, V value) {
		return (sourceList == null) ? null : (V) ArrayUtil.getNext(
				sourceList.toArray(), value, true);
	}

	/**
	 * 将内容倒置
	 * 
	 * @param sourceList
	 * @return
	 */
	public static <V> List<V> invertList(List<V> sourceList) {
		if (isEmpty(sourceList)) {
			return sourceList;
		}

		List<V> invertList = new ArrayList<V>(sourceList.size());
		for (int i = sourceList.size() - 1; i >= 0; i--) {
			invertList.add(sourceList.get(i));
		}
		return invertList;
	}
}
