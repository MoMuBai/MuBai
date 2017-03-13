package com.mb.mubai.ui.msg;

import com.mb.mubai.base.api.found.FoundFactory;
import com.mb.mubai.base.api.msg.MsgFactory;
import com.mb.mubai.ui.found.FoundContract;

import rx.Observable;

/**
 * Created by lzw on 2017/3/13.
 */

public class MsgModel implements MsgContract.Model {
    @Override
    public Observable<String> getData(String userId) {
        return MsgFactory.getMsgService().getMsg(userId);
    }
}
