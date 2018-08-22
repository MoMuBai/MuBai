package com.lzw.library.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * MapUtil
 *
 * @author wei2bei132
 */
public class MapUtil {

    /**
     * 默认键值间隔符号
     */
    public static final String DEFAULT_KEY_AND_VALUE_SEPARATOR = ":";

    /**
     * 默认键值对间隔符号
     */
    public static final String DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR = ",";

    /**
     * 判断是否为空
     *
     * @param sourceMap
     * @return
     */
    public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
        return (sourceMap == null || sourceMap.size() == 0);
    }

    /**
     * 添加键值对到Map中,但是key不能为null或者为空
     *
     * @param map
     * @param key
     * @param value
     * @return
     */
    public static boolean putMapNotEmptyKey(Map<String, String> map,
                                            String key, String value) {
        if (map == null || StringUtil.isEmpty(key)) {
            return false;
        }
        map.put(key, value);
        return true;
    }

    /**
     * 添加键值对到Map中,但是key和value不能为null或者为空
     *
     * @param map
     * @param key
     * @param value
     * @return
     */
    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map,
                                                    String key, String value) {
        if (map == null || StringUtil.isEmpty(key) || StringUtil.isEmpty(value)) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * 添加键值对到Map中,但是key不能为null或者为空，当value为空添加默认值
     *
     * @param map
     * @param key
     * @param value
     * @return
     */
    public static boolean putMapNotEmptyKeyAndValue(Map<String, String> map,
                                                    String key, String value, String defaultValue) {
        if (map == null || StringUtil.isEmpty(key)) {
            return false;
        }

        map.put(key, StringUtil.isEmpty(value) ? defaultValue : value);
        return true;
    }

    /**
     * 添加键值对到Map中,但是key不能为null
     *
     * @param map
     * @param key
     * @param value
     * @return
     */
    public static <K, V> boolean putMapNotNullKey(Map<K, V> map, K key, V value) {
        if (map == null || key == null) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * 添加键值对到Map中,但是key和value不能为null
     *
     * @param map
     * @param key
     * @param value
     * @return
     */
    public static <K, V> boolean putMapNotNullKeyAndValue(Map<K, V> map, K key,
                                                          V value) {
        if (map == null || key == null || value == null) {
            return false;
        }

        map.put(key, value);
        return true;
    }

    /**
     * 通过value找到对应key
     *
     * @param map
     * @param value
     * @return
     */
    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        if (isEmpty(map)) {
            return null;
        }

        for (Entry<K, V> entry : map.entrySet()) {
            if (ObjectUtil.isEquals(entry.getValue(), value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 解析映射键值对
     * <p>
     * <pre>
     * parseKeyAndValueToMap("","","",true)=null
     * parseKeyAndValueToMap(null,"","",true)=null
     * parseKeyAndValueToMap("a:b,:","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,:d","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,c:d","","",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",false)={(a, b),( c , d)}
     * parseKeyAndValueToMap("a=b, c=d","=", ",", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b; c=d","=", ";", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b, c=d", ",", ";", false)={(a=b, c=d)}
     * </pre>
     *
     * @param source
     * @param keyAndValueSeparator
     * @param keyAndValuePairSeparator
     * @param ignoreSpace
     * @return
     */
    public static Map<String, String> parseKeyAndValueToMap(String source,
                                                            String keyAndValueSeparator, String keyAndValuePairSeparator,
                                                            boolean ignoreSpace) {
        if (StringUtil.isEmpty(source)) {
            return null;
        }

        if (StringUtil.isEmpty(keyAndValueSeparator)) {
            keyAndValueSeparator = DEFAULT_KEY_AND_VALUE_SEPARATOR;
        }
        if (StringUtil.isEmpty(keyAndValuePairSeparator)) {
            keyAndValuePairSeparator = DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR;
        }
        Map<String, String> keyAndValueMap = new HashMap<String, String>();
        String[] keyAndValueArray = source.split(keyAndValuePairSeparator);
        if (keyAndValueArray == null) {
            return null;
        }

        int seperator;
        for (String valueEntity : keyAndValueArray) {
            if (!StringUtil.isEmpty(valueEntity)) {
                seperator = valueEntity.indexOf(keyAndValueSeparator);
                if (seperator != -1) {
                    if (ignoreSpace) {
                        MapUtil.putMapNotEmptyKey(keyAndValueMap, valueEntity
                                .substring(0, seperator).trim(), valueEntity
                                .substring(seperator + 1).trim());
                    } else {
                        MapUtil.putMapNotEmptyKey(keyAndValueMap,
                                valueEntity.substring(0, seperator),
                                valueEntity.substring(seperator + 1));
                    }
                }
            }
        }
        return keyAndValueMap;
    }

    /**
     * 解析映射键值对
     * <p>
     * <pre>
     * parseKeyAndValueToMap("","","",true)=null
     * parseKeyAndValueToMap(null,"","",true)=null
     * parseKeyAndValueToMap("a:b,:","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,:d","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,c:d","","",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",false)={(a, b),( c , d)}
     * parseKeyAndValueToMap("a=b, c=d","=", ",", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b; c=d","=", ";", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b, c=d", ",", ";", false)={(a=b, c=d)}
     * </pre>
     *
     * @param source
     * @param keyAndValueSeparator
     * @param keyAndValuePairSeparator
     * @param ignoreSpace
     * @return
     */
    public static Map<String, String> parseKeyAndValueToMap(String source,
                                                            boolean ignoreSpace) {
        return parseKeyAndValueToMap(source, DEFAULT_KEY_AND_VALUE_SEPARATOR,
                DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR, ignoreSpace);
    }

    /**
     * 解析映射键值对，忽略空格
     * <p>
     * <pre>
     * parseKeyAndValueToMap("","","",true)=null
     * parseKeyAndValueToMap(null,"","",true)=null
     * parseKeyAndValueToMap("a:b,:","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,:d","","",true)={(a,b)}
     * parseKeyAndValueToMap("a:b,c:d","","",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",true)={(a,b),(c,d)}
     * parseKeyAndValueToMap("a=b, c = d","=",",",false)={(a, b),( c , d)}
     * parseKeyAndValueToMap("a=b, c=d","=", ",", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b; c=d","=", ";", false)={(a,b),( c,d)}
     * parseKeyAndValueToMap("a=b, c=d", ",", ";", false)={(a=b, c=d)}
     * </pre>
     *
     * @param source
     * @param keyAndValueSeparator
     * @param keyAndValuePairSeparator
     * @param ignoreSpace
     * @return
     */
    public static Map<String, String> parseKeyAndValueToMap(String source) {
        return parseKeyAndValueToMap(source, DEFAULT_KEY_AND_VALUE_SEPARATOR,
                DEFAULT_KEY_AND_VALUE_PAIR_SEPARATOR, true);
    }

    /**
     * 将Map转换成Json
     *
     * @param map
     * @return
     */
    public static String toJson(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }

        StringBuilder paras = new StringBuilder();
        paras.append("{");
        Iterator<Entry<String, String>> ite = map.entrySet().iterator();
        while (ite.hasNext()) {
            Entry<String, String> entry = (Entry<String, String>) ite
                    .next();
            paras.append("\"").append(entry.getKey()).append("\":")
                    .append(entry.getValue());
            if (ite.hasNext()) {
                paras.append(",");
            }
        }
        paras.append("}");
        return paras.toString();
    }
}
