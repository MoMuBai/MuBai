package com.lzw.mode_user.user;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.lzw.mode_user.GetUserInfo;

/**
 * Author: lzw
 * Date: 2018/6/14
 * Description: This is UserService
 */

public class UserService extends Service {

    private IBinder iBinder = new GetUserInfo.Stub() {

        @Override
        public void setUserName(String name) throws RemoteException {

        }

        @Override
        public String getUserName() throws RemoteException {
            return "沐白";
        }

        @Override
        public String getUserPhone() throws RemoteException {
            return "头像";
        }

        @Override
        public void setUserPhone(String phone) throws RemoteException {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }
}
