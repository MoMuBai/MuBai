package com.mob.mubai.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.To;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import io.rong.imkit.RongIM;

public class MainActivity extends BaseActivity {

    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    @Bind(R.id.img1)
    ImageView img1;
    @Bind(R.id.txt1)
    TextView txt1;
    @Bind(R.id.back)
    RelativeLayout back;
    @Bind(R.id.img2)
    TextView img2;
    @Bind(R.id.rl)
    RelativeLayout rl;
    private List<String> data;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        img1.setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        data.add(0, "SeekBarActivity");
        data.add(1, "LoginActivity");
        data.add(2, "OtherActivity");
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data));
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
                To.d("OtherActivity");
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {


            final AlertDialog.Builder alterDialog = new AlertDialog.Builder(this);
            alterDialog.setMessage("确定退出应用？");
            alterDialog.setCancelable(true);

            alterDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (RongIM.getInstance() != null)
                        RongIM.getInstance().disconnect(true);
                        Process.killProcess(Process.myPid());
                }
            });
            alterDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alterDialog.show();
        }

        return false;
    }

}
