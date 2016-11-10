package com.mob.mubai.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.To;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    private List<String> data;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add("沐白" + i);
        }
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data));
    }

    @OnItemClick(R.id.list_view)
    void itemClick(int pos) {
        switch (pos) {
            case 0:
                startIntent(SeekBarActivity.class);
                break;
            case 1:
                startIntent(LoginActivity.class);
                break;
            case 2:
                startIntent(IMActivity.class);
                break;
            case 3:
                To.d("沐白"+pos);
                break;
            case 4:
                To.d("沐白"+pos);
                break;
            default:
                break;
        }
    }


}
