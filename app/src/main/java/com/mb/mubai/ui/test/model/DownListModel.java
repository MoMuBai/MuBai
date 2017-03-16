package com.mb.mubai.ui.test.model;

import com.mb.mubai.ui.test.contract.DownListContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lzw
 * //
 * @date: 2017/3/16 下午4:16
 * //
 * @desc:
 */

public class DownListModel implements DownListContract.Model {

    private List<String> data;

    @Override
    public List<String> getData(String type) {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add("测试----" + type);
        }
        return data;
    }

    @Override
    public List<String> getMainData() {
        data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("Main");
        }
        return data;
    }
}
