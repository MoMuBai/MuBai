package com.mob.mubai.view.widget;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.mob.mubai.R;


/**
 * Created by lzw on 2016/12/20.
 * 备注： 加载dialog
 */
public final class LoadingDialog extends Dialog {
        public LoadingDialog(Context context) {
                super(context);
        }

        public LoadingDialog(Context context, int themeResId) {
                super(context, themeResId);
        }

        protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
                super(context, cancelable, cancelListener);
        }

        public static class Builder {

                private Context context;
                private LinearLayout loadLayout;
                private LoadingDialog dialog;

                public Builder(Context context) {
                        this.context = context;
                }

                public LoadingDialog create() {
                        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
                        dialog = new LoadingDialog(context, R.style.Dialog);
                        loadLayout = (LinearLayout) view.findViewById(R.id.loadLayout);
                        dialog.setOnDismissListener(new OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialog) {
                                        loadLayout.setVisibility(View.INVISIBLE);
                                        dialog.dismiss();
                                }
                        });
                        dialog.setOnShowListener(new OnShowListener() {
                                @Override
                                public void onShow(DialogInterface dialog) {
                                        loadLayout.setVisibility(View.VISIBLE);
                                }
                        });
                        // 点击外部区域关闭
                        dialog.setCanceledOnTouchOutside(false);
                        // 点击返回键对话框关闭
                        dialog.setCancelable(false);
                        dialog.setContentView(view);
                        return dialog;
                }
        }
}
