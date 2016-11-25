package com.mob.mubai.base.listeners;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by lzw on 2016/11/10.
 * PopWidnow 点击事件
 */
public interface PopListClickListener {

    void myOnClick(AdapterView<?> parent, View view, int position, long id);
}
