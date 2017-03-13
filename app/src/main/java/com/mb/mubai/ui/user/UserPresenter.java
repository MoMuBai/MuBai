package com.mb.mubai.ui.user;

import com.mb.mubai.base.api.SchedulersCompat;
import com.mb.mubai.ui.msg.MsgContract;

/**
 * Created by lzw on 2017/3/13.
 */

public class UserPresenter extends UserContract.Presenter {
    @Override
    protected void onStart() {

    }

    @Override
    void getUserInfo(String userId) {
        mRxManager.add(mModel.getUserInfo(userId)
                .compose(SchedulersCompat.applyIoSchedulers())
                .subscribe(s -> {
                    mView.showUserInfo(s);
                }));
    }
}
