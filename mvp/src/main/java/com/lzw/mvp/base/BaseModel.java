package com.lzw.mvp.base;

/**
 * Author: lzw
 * Date: 2018/3/9
 * Description: This is BaseModel
 */

public abstract class BaseModel<T> {

    public String[] params;

    public BaseModel setParams(String... params) {
        this.params = params;
        return this;
    }

    public abstract void execute(BaseCallBack<T> baseCallBack);
}
