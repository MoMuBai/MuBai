// ICalcAIDL.aidl
package com.lzw.kotlin.aidl;

// Declare any non-default types here with import statements

interface ICalcAIDL {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

            //计算num1 + num2
                int add(int num1,int num2);
}
