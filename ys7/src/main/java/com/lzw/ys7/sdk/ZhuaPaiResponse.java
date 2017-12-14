package com.lzw.ys7.sdk;

/**
 * @author: lzw
 * @date: 14/12/2017 11:49 AM
 * @desc:
 */

public class ZhuaPaiResponse {
    private String code;
    private String msg;
    private DataValue data;

    public DataValue getData() {
        return data;
    }

    public void setData(DataValue data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class DataValue {
        private String picUrl;

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }
    }
}
