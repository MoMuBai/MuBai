package com.lzw.library.utils;

/**
 * ArrayUtil
 *
 * @author wei2bei132
 */
public class ArrayUtil {
    /**
     * 判断是否为空
     *
     * @param sourceArray
     * @return
     */
    public static <V> boolean isEmpty(V[] sourceArray) {
        return (sourceArray == null || sourceArray.length == 0);
    }

    /**
     * 获取某个值的上一个值
     *
     * @param sourceArray
     * @param value
     * @param defaultValue
     * @param isCircle
     * @return
     */
    public static <V> V getLast(V[] sourceArray, V value, V defaultValue,
                                boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }

        int currentPosition = -1;
        for (int i = 0; i < sourceArray.length; i++) {
            if (ObjectUtil.isEquals(value, sourceArray[i])) {
                currentPosition = i;
                break;
            }
        }
        if (currentPosition == -1) {
            return defaultValue;
        }

        if (currentPosition == 0) {
            return isCircle ? sourceArray[sourceArray.length - 1]
                    : defaultValue;
        }
        return sourceArray[currentPosition - 1];
    }

    /**
     * 获取某个值的下一个值
     *
     * @param sourceArray
     * @param value
     * @param defaultValue
     * @param isCircle
     * @return
     */
    public static <V> V getNext(V[] sourceArray, V value, V defaultValue,
                                boolean isCircle) {
        if (isEmpty(sourceArray)) {
            return defaultValue;
        }

        int currentPosition = -1;
        for (int i = 0; i < sourceArray.length; i++) {
            if (ObjectUtil.isEquals(value, sourceArray[i])) {
                currentPosition = i;
                break;
            }
        }
        if (currentPosition == -1) {
            return defaultValue;
        }

        if (currentPosition == sourceArray.length - 1) {
            return isCircle ? sourceArray[0] : defaultValue;
        }
        return sourceArray[currentPosition + 1];
    }

    public static <V> V getLast(V[] sourceArray, V value, boolean isCircle) {
        return getLast(sourceArray, value, null, isCircle);
    }

    public static <V> V getNext(V[] sourceArray, V value, boolean isCircle) {
        return getNext(sourceArray, value, null, isCircle);
    }

    public static long getLast(long[] sourceArray, long value,
                               long defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException(
                    "The length of source array must be greater than 0.");
        }

        Long[] array = ObjectUtil.transformLongArray(sourceArray);
        return getLast(array, value, defaultValue, isCircle);

    }

    public static long getNext(long[] sourceArray, long value,
                               long defaultValue, boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException(
                    "The length of source array must be greater than 0.");
        }
        Long[] array = ObjectUtil.transformLongArray(sourceArray);
        return getNext(array, value, defaultValue, isCircle);
    }

    public static int getLast(int[] sourceArray, int value, int defaultValue,
                              boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException(
                    "The length of source array must be greater than 0.");
        }

        Integer[] array = ObjectUtil.transformIntArray(sourceArray);
        return getLast(array, value, defaultValue, isCircle);
    }

    public static int getNext(int[] sourceArray, int value, int defaultValue,
                              boolean isCircle) {
        if (sourceArray.length == 0) {
            throw new IllegalArgumentException(
                    "The length of source array must be greater than 0.");
        }

        Integer[] array = ObjectUtil.transformIntArray(sourceArray);
        return getNext(array, value, defaultValue, isCircle);
    }
}
