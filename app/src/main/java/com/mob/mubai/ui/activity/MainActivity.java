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
import android.widget.Toast;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.AppManager;
import com.mob.mubai.base.utils.To;
import com.mob.mubai.view.dialog.MyDialog;

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
    private int backPressTimes;

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

    /**
     * 点击返回键的事件
     */
    @Override
    public void onBackPressed() {
        if (backPressTimes == 0) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            backPressTimes = 1;
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        backPressTimes = 0;
                    }
                }
            }.start();
            return;
        } else {
            if (RongIM.getInstance() != null)
                RongIM.getInstance().disconnect(true);
            AppManager.getAppManager().finishAllActivity();
            Process.killProcess(Process.myPid());
        }
        super.onBackPressed();
    }
}
