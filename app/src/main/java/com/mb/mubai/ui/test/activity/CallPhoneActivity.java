package com.mb.mubai.ui.test.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Button;

import com.mb.mubai.R;
import com.mb.mubai.base.BaseActivity;
import com.mb.mubai.base.BaseModel;
import com.mb.mubai.base.BasePresenter;

import java.util.List;
import java.util.Set;

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
    protected BaseModel getModel() {
        return null;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_callphone;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        getBlueToothDeviceSet();
    }

    private Set<BluetoothDevice> getBlueToothDeviceSet() {
        Set<BluetoothDevice> bluetoothDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        return bluetoothDevices;
    }

    @OnClick({R.id.btn_callPhone1, R.id.btn_callPhone2})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_callPhone1:





//                Intent intent1 = new Intent(Intent.ACTION_CALL);
//                Uri data1 = Uri.parse("tel:10101688" + PhoneNumberUtils.PAUSE + "18662");
//                intent1.setData(data1);
//                if (Build.VERSION.SDK_INT > 22) {
//                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    ActivityCompat#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for ActivityCompat#requestPermissions for more details.
//                        return;
//                    }
//                    startActivity(intent1);
//                }
                Intent intent1 = new Intent(Intent.ACTION_CALL);
                Uri data1 = Uri.parse("tel:10101688" + PhoneNumberUtils.PAUSE + "18662");
                intent1.setData(data1);
                if (Build.VERSION.SDK_INT > 22) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent1);
                }
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
