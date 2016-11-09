package com.mob.mubai.data.model;

/**
 * Created by mubai on 2016/11/3.
 * 算术类
 */

public class Calculator {

    //加
    public int sum(int a, int b){
        return a + b;
    }

    //减
    public int substract(int a, int b){
        return a - b;
    }

    //乘
    public int multiply(int a, int b){
        return a * b;
    }

    //除
    public int divide(int a, int b){
        if (b == 0) throw new IllegalArgumentException("Dividor cannot be 0");
        return a / b;
    }

}
