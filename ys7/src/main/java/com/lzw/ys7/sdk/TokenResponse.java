package com.lzw.ys7.sdk;

/**
 * @author: lzw
 * @date: 14/12/2017 11:02 AM
 * @desc:
 */

public class TokenResponse {
    private String code;
    private String msg;

    private DataValue data;

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

    public DataValue getData() {
        return data;
    }

    public void setData(DataValue data) {
        this.data = data;
    }

    public class DataValue {
        private String accessToken;
        private String expireTime;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }
    }
}
