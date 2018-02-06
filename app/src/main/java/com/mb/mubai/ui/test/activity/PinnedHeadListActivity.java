package com.mb.mubai.ui.test.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.lzw.pinnedhead.IndexBar;
import com.lzw.pinnedhead.PinnedHeaderListView;
import com.lzw.pinnedhead.TestSectionedAdapter;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.ui.test.contract.PinnedHeadContract;
import com.mb.mubai.ui.test.model.PinnedHeadModel;
import com.mb.mubai.ui.test.presenter.PinnedHeadPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/1/18
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc  带头部效果以及索引条的ListView
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class PinnedHeadListActivity extends BaseActivity<PinnedHeadPresenter, PinnedHeadModel> implements PinnedHeadContract.View {
    @Bind(R.id.pinned_head)
    PinnedHeaderListView pinnedHead;
    @Bind(R.id.index_bar)
    IndexBar indexBar;
    @Bind(R.id.tv_search)
    TextView tvSearch;

    private TestSectionedAdapter mAdapter;

    private Map<Integer, List<String>> map;

    private List<String> list, hotCityList;

    @Override
    protected PinnedHeadModel getModel() {
        return new PinnedHeadModel();
    }

    @Override
    protected PinnedHeadPresenter getPresenter() {
        return new PinnedHeadPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_pinnedhead;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        map = new HashMap<>();
        list = new ArrayList<>();
        hotCityList = new ArrayList<>();
        mPresenter.getData();
    }

    @Override
    public void showData(String[] headData, String[][] data) {
        for (int i = 0; i < headData.length; i++) {
            hotCityList.add(headData[i]);
        }
        for (int i = 0; i < hotCityList.size(); i++) {
            list = new ArrayList<>();
            for (int j = 0; j < data[i].length; j++) {
                list.add(data[i][j]);
            }
            map.put(i, list);
        }
        mAdapter = new TestSectionedAdapter(this, hotCityList, map);
        pinnedHead.setAdapter(mAdapter);
        indexBar.setOnIndexBarChangeListener(new IndexBar.OnIndexBarChangeListener() {
            @Override
            public void onIndexChange(int index, String word) {
                for (int i = 0; i < headData.length; i++) {
                    if (word.endsWith("#")) {
                        pinnedHead.setSelection(0);
                    }
                    if (word.endsWith("热门")) {
                        pinnedHead.setSelection(1);
                    } else if (word.endsWith(headData[i])) {
                        int rightSection = 0;
                        for (int j = 0; j < index; j++) {
                            rightSection += mAdapter.getCountForSection(j) + 1;
                        }
                        pinnedHead.setSelection(rightSection - map.get(i).size());
                    }
                    tvSearch.setVisibility(View.VISIBLE);
                    tvSearch.setText(word);
                    handler.removeCallbacksAndMessages(null);//发送之前先移除之前所有发送的消息,防止突然莫名其妙消失了
                    handler.sendEmptyMessageDelayed(1, 1500);//1.5秒后隐藏界面
                }
                return;//记得return防止 for 循环继续执行,导致滚动到的不是第一个而是最后一个
            }
        });
    }

    @Override
    public void showIndex(String[] index) {
        indexBar.setWords(index);
    }

    @Override
    public void showStart() {

    }

    @Override
    public void showNoData(String msg) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showStop() {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (null != tvSearch) {
                tvSearch.setVisibility(View.GONE);
            }
        }
    };

}
