package com.mb.mubai.ui.user;

import com.mb.mubai.base.api.msg.MsgFactory;
import com.mb.mubai.base.api.user.UserFactory;
import com.mb.mubai.ui.msg.MsgContract;

import rx.Observable;

/**
 * Created by lzw on 2017/3/13.
 */

public class UserModel implements UserContract.Model {
    @Override
    public Observable<String> getUserInfo(String userId) {
        return UserFactory.getUserService().getUserInfo(userId);
    }
}
