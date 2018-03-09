package com.lzw.mvp;

import com.lzw.mvp.base.BaseCallBack;
import com.lzw.mvp.base.BaseModel;

/**
 * Author: lzw
 * Date: 2018/3/9
 * Description: This is MvpModel
 */

public class MvpModel extends BaseModel<String> {

    @Override
    public void execute(BaseCallBack<String> baseCallBack) {
        if (true) {
            baseCallBack.onSuccess("成功！");
        } else {
            baseCallBack.onFailure("失败");
        }
    }
}
