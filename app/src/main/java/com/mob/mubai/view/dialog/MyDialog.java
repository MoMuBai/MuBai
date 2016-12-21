package com.mob.mubai.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mob.mubai.R;
import com.mob.mubai.base.listeners.DialogNoBtnListener;
import com.mob.mubai.base.listeners.DialogOkBtnListener;


/**
 * Created by lzw on 2016/11/10.
 * Dialog
 */
public final class MyDialog extends Dialog {
        public MyDialog(Context context) {
                super(context);
        }

        public MyDialog(Context context, int themeResId) {
                super(context, themeResId);
        }

        protected MyDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
                super(context, cancelable, cancelListener);
        }

        public static class Builder {

                private Context context;
                private DialogOkBtnListener okBtnListener;
                private DialogNoBtnListener noBtnListener;
                private TextView tv_ok, tv_cancle, tv_title;
                private String title;

                public Builder(Context context, String title) {
                        this.context = context;
                        this.title = title;
                }

                public Builder setOkButton(DialogOkBtnListener listener) {
                        this.okBtnListener = listener;
                        return this;
                }

                public Builder setNoButton(DialogNoBtnListener noBtnListener) {
                        this.noBtnListener = noBtnListener;
                        return this;
                }

                public MyDialog create() {
                        View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
                        final MyDialog dialog = new MyDialog(context, R.style.Dialog);

                        tv_ok = (TextView) view.findViewById(R.id.tv_determine);
                        tv_cancle = (TextView) view.findViewById(R.id.tv_cancel);
                        tv_title = (TextView) view.findViewById(R.id.tv_title);
                        tv_title.setText(title);

                        if (okBtnListener != null) {
                                tv_ok.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                                okBtnListener.DealSom("");
                                        }
                                });
                        }
                        tv_cancle.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        noBtnListener.DealSom("");
                                }
                        });
                        dialog.setContentView(view);

                        return dialog;
                }
        }
}
