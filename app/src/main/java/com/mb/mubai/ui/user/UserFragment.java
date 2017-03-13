package com.mb.mubai.ui.user;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lzw.library.utils.To;
import com.mb.mubai.R;
import com.mb.mubai.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;


/**
 * //////////////////////////////////////////////////////////////////////////////
 * //
 * //      ┏┛ ┻━━━━━┛ ┻┓
 * //      ┃　　　　　　 ┃
 * //      ┃　　　━　　　┃
 * //      ┃　┗┛　  ┗┛　┃
 * //      ┃　　　　　　 ┃
 * //      ┃　　　┻　　　┃               @Author  林志文
 * //      ┃　　　　　　 ┃
 * //      ┗━┓　　　┏━━━┛               @Date  2016/12/21
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
@RuntimePermissions
public class UserFragment extends BaseFragment<UserPresenter, UserModel> implements UserContract.View {


    @Bind(R.id.btn_take)
    Button btnTake;
    @Bind(R.id.btn_get)
    Button btnGet;

    public static Fragment getFragment() {
        Fragment f = new UserFragment();
        return f;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoad() {
        mPresenter.getUserInfo("userInfo");
    }


    @OnClick({R.id.btn_take, R.id.btn_get})
    void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take:
            case R.id.btn_get:
                UserFragmentPermissionsDispatcher.showCameraWithCheck(this);
                break;
            default:
                break;
        }
    }


    /**
     * 请求权限成功后的处理
     */
    @NeedsPermission(Manifest.permission.CAMERA)
    void showCamera() {
        To.d("请求相机权限成功");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        UserFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * dialog显示为什么需要权限
     *
     * @param request
     */
    @OnShowRationale(Manifest.permission.CAMERA)
    void showDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getActivity())
                .setMessage("权限通知")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();//再次执行请求
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .show();
    }

    /**
     * 权限拒绝
     */
    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showDenied() {
        To.d("申请权限被拒绝");
    }

    /**
     * 用户选择不再询问
     */
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNotAsk() {
        To.d("用户不再询问");
    }

    @Override
    public void showUserInfo(String data) {
        To.d(data);
    }

    @Override
    public void showStart() {

    }

    @Override
    public void showNoData(String msg) {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showStop() {

    }
}
