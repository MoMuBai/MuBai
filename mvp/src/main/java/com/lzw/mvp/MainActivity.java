package com.lzw.mvp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lzw.mvp.base.BaseActivity;

/**
 * @author: lzw
 * @date: 09/12/2017 12:56 PM
 * @desc:
 */

public class MainActivity extends BaseActivity<MvpPresenter> implements MvpView {

    @butterknife.Bind(R.id.get)
    TextView get;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getData("params");
            }
        });
    }

    @Override
    protected MvpPresenter getPresenter() {
        return new MvpPresenter();
    }

    @Override
    public void showLoad() {
//        progressDialog.show();
    }

    @Override
    public void showData(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showMvpInfo() {

    }
}
