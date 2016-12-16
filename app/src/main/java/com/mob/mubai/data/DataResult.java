package com.mob.mubai.data;

/**
 * Created by lzw on 2016/11/8.
 */

public class DataResult<T> {
        private String code;
        private String msg;
        private T data;

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

        public T getData() {
                return data;
        }

        public void setData(T data) {
                this.data = data;
        }
}
