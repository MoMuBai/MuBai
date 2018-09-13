package com.mb.mubai.ui.test.activity;

import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;
import com.mb.mubai.data.bean.BrokenLineCusVisit;
import com.mb.mubai.view.widget.BrokenLineCusVisitView;
import com.mb.mubai.view.widget.BrokenLineCusVisitView2;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Author: lzw
 * Date: 2018/7/6
 * Description: This is TestActivity
 */

public class TestActivity extends BaseActivity {


    @Bind(R.id.brokenline)
    BrokenLineCusVisitView brokenline;
    @Bind(R.id.brokenline2)
    BrokenLineCusVisitView2 brokenline2;

    private List<BrokenLineCusVisit> mdata, mdata2;

    @Override
    protected BaseModel getModel() {
        return null;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        mdata = new ArrayList<>();
        mdata2 = new ArrayList<>();
    }

    @Override
    protected void initData() {
        BrokenLineCusVisit brokenline1 = new BrokenLineCusVisit("1", 40);
        BrokenLineCusVisit brokenline2 = new BrokenLineCusVisit("2", 25);
        BrokenLineCusVisit brokenline3 = new BrokenLineCusVisit("3", 33);
        BrokenLineCusVisit brokenline8 = new BrokenLineCusVisit("4", 20);
        BrokenLineCusVisit brokenline4 = new BrokenLineCusVisit("5", 16);
        BrokenLineCusVisit brokenline5 = new BrokenLineCusVisit("6", 28);
        BrokenLineCusVisit brokenline6 = new BrokenLineCusVisit("7", 22);
        BrokenLineCusVisit brokenline7 = new BrokenLineCusVisit("8", 15);
        BrokenLineCusVisit brokenline9 = new BrokenLineCusVisit("8", 6);

        mdata.add(brokenline1);
        mdata.add(brokenline9);
        mdata.add(brokenline2);
        mdata.add(brokenline1);
        mdata.add(brokenline9);
        mdata.add(brokenline3);
        mdata.add(brokenline8);
        mdata.add(brokenline1);
        mdata.add(brokenline9);
        mdata.add(brokenline4);
        mdata.add(brokenline1);
        mdata.add(brokenline9);
        mdata.add(brokenline5);
        mdata.add(brokenline6);
        mdata.add(brokenline1);
        mdata.add(brokenline7);
        mdata.add(brokenline9);

        mdata2.add(brokenline1);
        mdata2.add(brokenline2);
        mdata2.add(brokenline3);
        mdata2.add(brokenline2);
        mdata2.add(brokenline9);
        mdata2.add(brokenline8);
        mdata2.add(brokenline3);
        mdata2.add(brokenline4);
        mdata2.add(brokenline6);
        mdata2.add(brokenline9);
        mdata2.add(brokenline1);
        mdata2.add(brokenline9);
        mdata2.add(brokenline3);
        mdata2.add(brokenline5);
        mdata2.add(brokenline3);
        mdata2.add(brokenline9);

        brokenline.setMdata(mdata);
        this.brokenline2.setMdata(mdata2);

    }
}
