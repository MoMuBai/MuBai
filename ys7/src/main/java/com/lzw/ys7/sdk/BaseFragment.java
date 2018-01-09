package com.lzw.ys7.sdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lzw.ys7.Ys7App;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author: lzw
 * @date: 22/12/2017 2:35 PM
 * @desc:
 */

public class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = Ys7App.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
