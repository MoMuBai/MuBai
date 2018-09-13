package com.mb.mubai.data.bean;

/**
 * Author: lzw
 * Date: 2018/9/10
 * Description: This is BrokenLineCusVisit
 */

public class BrokenLineCusVisit {
    //拜访日期
    private String date;
    //拜访数量
    private int data;

    public BrokenLineCusVisit(String date, int data) {
        this.date = date;
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
