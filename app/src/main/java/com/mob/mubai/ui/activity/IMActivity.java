package com.mob.mubai.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mob.mubai.R;
import com.mob.mubai.base.BaseActivity;
import com.mob.mubai.base.utils.L;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by lzw on 2016/11/9.
 */

public class IMActivity extends BaseActivity {

    @Bind(R.id.btn1)
    Button btn1;
    @Bind(R.id.btn2)
    Button btn2;
    @Bind(R.id.btn3)
    Button btn3;
    @Bind(R.id.btn4)
    Button btn4;
    @Bind(R.id.btn5)
    Button btn5;

    @Override
    protected int getLayout() {
        return R.layout.activity_im;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5})
    protected void onClick(View v){
        switch (v.getId()){
            case R.id.btn1:
                /**
                 * 启动单聊
                 * context - 应用上下文。
                 * targetUserId - 要与之聊天的用户 Id。
                 * title - 聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                 */
                if (RongIM.getInstance() != null)
                    RongIM.getInstance().startPrivateChat(this, "26594", "title");
                break;
            case R.id.btn2:
                //启动会话列表界面
                if (RongIM.getInstance() != null)
                    RongIM.getInstance().startConversationList(this);
                break;
            case R.id.btn3:
                //启动聚合会话列表界面
                if (RongIM.getInstance() != null)
                    RongIM.getInstance().startSubConversationList(this, Conversation.ConversationType.GROUP);
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
            default:
                break;
        }
    }


}
