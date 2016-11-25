package com.mob.mubai.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;

import io.rong.imlib.model.Conversation;

/**
 * Created by lzw on 2016/11/9.
 */

public class ConversationActivity extends BaseActivity{

    /**
     * 目标 Id
     */
    private String mTargetId;

    /**
     * 刚刚创建完讨论组后获得讨论组的id 为targetIds，需要根据 为targetIds 获取 targetId
     */
    private String mTargetIds;

    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;

    protected int getLayout() {
        return R.layout.conversation;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
