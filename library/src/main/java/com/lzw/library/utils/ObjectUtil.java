package com.lzw.library.utils;

/**
 * ObjectUtil
 *
 * @author wei2bei132
 */
public class ObjectUtil {

    /**
     * 判断是否相等
     *
     * @param actual
     * @param expected
     * @return
     */
    public static boolean isEquals(Object actual, Object expected) {
        return actual == null ? expected == null : actual.equals(expected);
    }

    /**
     * 转换long数组到Long数组
     *
     * @param source
     * @return
     */
    public static Long[] transformLongArray(long[] source) {
        Long[] destin = new Long[source.length];
        for (int i = 0; i < source.length; i++) {
            destin[i] = source[i];
        }
        return destin;
    }

    /**
     * 转换Long数组到long数组
     *
     * @param source
     * @return
     */
    public static long[] transformLongArray(Long[] source) {
        long[] destin = new long[source.length];
        for (int i = 0; i < source.length; i++) {
            destin[i] = source[i];
        }
        return destin;
    }

    /**
     * 转换int数组到Integer数组
     *
     * @param source
     * @return
     */
    public static Integer[] transformIntArray(int[] source) {
        Integer[] destin = new Integer[source.length];
        for (int i = 0; i < source.length; i++) {
            destin[i] = source[i];
        }
        return destin;
    }

    /**
     * 转换Integer数组到int数组
     *
     * @param source
     * @return
     */
    public static int[] transformIntArray(Integer[] source) {
        int[] destin = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            destin[i] = source[i];
        }
        return destin;
    }

    /**
     * 比较两个大小
     * <p>
     * <pre>
     * 如果v1 > v2 返回 1
     * 如果v1 = v2 返回 0
     * 如果v1 < v2 返回 -1
     * 如果v1==null v2==null 返回 0
     * 如果v1==null v2!=nukl 返回 -1
     * 如果v1!=null v2==null 返回 1
     * </pre>
     *
     * @param v1
     * @param v2
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <V> int compare(V v1, V v2) {
        return v1 == null ? (v2 == null ? 0 : -1) : (v2 == null ? 1
                : ((Comparable) v1).compareTo(v2));
    }
}
