package com.mb.mubai.ui.test.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;

import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  lzw
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2017/3/8
 * //        ┃　　　┃   神兽保佑
 * //        ┃　　　┃   代码无BUG！      @Desc
 * //        ┃　　　┗━━━━━━━━━┓
 * //        ┃　　　　　　　    ┣━━━┛
 * //        ┃　　　　         ┏┛
 * //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
 * //          ┃ ┫ ┫   ┃ ┫ ┫
 * //          ┗━┻━┛   ┗━┻━┛
 * //
 * /////////////////////////////////////////////////////////////////////////////
 */
public class CallPhoneActivity extends BaseActivity {
    @Bind(R.id.btn_callPhone1)
    Button btnCallPhone1;
    @Bind(R.id.btn_callPhone2)
    Button btnCallPhone2;

    @Override
    protected int getLayout() {
        return R.layout.activity_callphone;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.btn_callPhone1, R.id.btn_callPhone2})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_callPhone1:
                Intent intent1 = new Intent(Intent.ACTION_CALL);
                Uri data1 = Uri.parse("tel:10101688" + PhoneNumberUtils.PAUSE + "18662");
                intent1.setData(data1);
                startActivity(intent1);
                break;
            case R.id.btn_callPhone2:
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                Uri data2 = Uri.parse("tel:10101688" + PhoneNumberUtils.PAUSE + "18662");
                intent2.setData(data2);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
