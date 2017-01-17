package com.mob.mubai.base;

/**
 * Created by lzw on 2016/11/7.
 */

public interface BaseView {
        void showStart();

        void showNoData(String msg);

        void showError(String msg);

        void showStop();
}
