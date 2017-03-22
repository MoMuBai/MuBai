package com.mb.mubai.ui.test.model;

import com.mb.mubai.ui.test.contract.ExpandableContract;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lzw
 * //
 * @date: 2017/3/22 上午9:48
 * //
 * @desc:
 */

public class ExpandableModel implements ExpandableContract.Model {
    private List<String> data;

    @Override
    public List<String> getData() {
        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("有一段青春，留作永恒。剪一段时光，放在最美的段落。好好保存留作永远，时间过去岁月的路过。只是多了一道曾经，流年一路改变了我们的模样。送走了我们的青春，留给了我们曾经。这样的夜别样的心情，倒影的年华。深夜想用文字表达我内心的段落，可是终究写不出整个心情！");
        }
        return data;
    }
}
