package com.mb.mubai.ui.found;

import com.mb.mubai.base.api.found.FoundFactory;
import com.mb.mubai.base.api.home.HomeFactory;
import com.mb.mubai.ui.home.HomeContract;

import rx.Observable;

/**
 * Created by lzw on 2017/3/13.
 */

public class FoundModel implements FoundContract.Model {
    @Override
    public Observable<String> getData(String userId) {
        return FoundFactory.getFoundService().getFoundData(userId);
    }
}
