package com.mb.mubai.ui.home;

import com.mb.mubai.base.api.home.HomeFactory;

import rx.Observable;

/**
 * Created by lzw on 2017/3/13.
 */

public class HomeModel implements HomeContract.Model {
    @Override
    public Observable<String> getData(String userId) {
        return HomeFactory.getHomeService().getHomeData(userId);
    }
}
