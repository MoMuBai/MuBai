package com.mb.mubai.ui.test.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lzw.library.base.BaseAdapter;
import com.mb.mubai.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author: lzw
 * @date: 27/12/2017 2:52 PM
 * @desc:
 */

public class PopupActivity extends AppCompatActivity {


    @Bind(R.id.list_view)
    GridView listView;
    private PopupWindow mPopupWindow, mPopupWindow2;
    /**
     * PopupWindow的View
     */
    private View popView;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_list);
        ButterKnife.bind(this);
        popView = LayoutInflater.from(this).inflate(R.layout.popup_layout, null);
        mPopupWindow = new PopupWindow(popView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow2 = new PopupWindow(popView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow2.setOutsideTouchable(true);
        //解决isShowing失效问题
        mPopupWindow.setFocusable(true);
        mPopupWindow2.setFocusable(true);
//        Button floatingButton = new Button(this);
//        floatingButton.setPadding(20, 20, 20, 20);
//        floatingButton.setText("button");
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                0, 0,
//                PixelFormat.TRANSPARENT
//        );
//        // flag 设置 Window 属性
//        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//        // type 设置 Window 类别（层级）
//        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
//        layoutParams.gravity = Gravity.CENTER;
//        WindowManager windowManager = getWindowManager();
//        windowManager.addView(floatingButton, layoutParams);
//        floatingButton.setOnClickListener(v -> {
//            mPopupWindow.setBackgroundDrawable(null);
//            mPopupWindow.showAtLocation(nounText, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, layout.getHeight());
//        });

        data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        data.add("");

        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        myAdapter.addRefreshData(data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private List<String> data;

    class MyAdapter extends BaseAdapter<String, MyAdapter.ViewHolder> {


        public MyAdapter(Context context) {
            super(context);
        }

        @SuppressLint("NewApi")
        @Override
        protected void onBindData(ViewHolder holder, String data, int pos) {
            holder.text.setOnClickListener(v -> {
                if (!mPopupWindow.isShowing()) {
                    mPopupWindow.showAsDropDown(holder.text, 0, 0, Gravity.BOTTOM);
                }
            });
        }

        @Override
        protected ViewHolder onCreateHolder(View view) {
            return new ViewHolder(view);
        }

        @Override
        protected int onItemCreate() {
            return R.layout.item_pop_layout;
        }

        class ViewHolder extends BaseAdapter.ViewHolder {
            @Bind(R.id.text)
            TextView text;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
