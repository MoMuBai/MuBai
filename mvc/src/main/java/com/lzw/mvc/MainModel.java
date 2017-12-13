package com.lzw.mvc;

/**
 * @author: lzw
 * @date: 09/12/2017 11:30 AM
 * @desc: Main-Model
 */

public class MainModel {
    private String userName;

    private int userId;

    public MainModel() {
    }

    public MainModel(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
