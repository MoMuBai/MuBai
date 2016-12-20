package com.mob.mubai.ui.presenter;

import com.mob.mubai.base.helps.DataResultFunc;
import com.mob.mubai.base.utils.L;
import com.mob.mubai.base.utils.UserManager;
import com.mob.mubai.data.DataResult;
import com.mob.mubai.data.bean.LoginValue;
import com.mob.mubai.data.model.PasswordValidator;
import com.mob.mubai.ui.contract.LoginContract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lzw on 2016/11/25.
 */

public class LoginPresenter extends LoginContract.Presenter {

        private UserManager userManager;
        private PasswordValidator passwordValidator;

        public LoginPresenter(UserManager userManager, PasswordValidator passwordValidator) {
                this.userManager = userManager;
                this.passwordValidator = passwordValidator;
        }

        @Override
        public void setData() {
                mView.showData(mModel.getData());
        }

        @Override
        public void login(String name, String pass) {
                mRxManager.add(mModel.login(name, pass)
                          .map(new DataResultFunc<LoginValue>())
                          .subscribeOn(Schedulers.io())
                          .observeOn(AndroidSchedulers.mainThread())
                          .subscribe(loginValue -> {
                                  mView.login(loginValue);
                          }));
        }

        @Override
        public void getJsonFromObject(String name, String pass) {
//
//                /**
//                 * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
//                 * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
//                 * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
//                 * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
//                 * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
//                 * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
//                 */
//                ObjectMapper mapper = new ObjectMapper();
//                try {
//                        String json = mapper.writeValueAsString(mModel.getLoginValue(name, pass));//类转Json
//                        List<LoginValue> loginValues = new ArrayList<>();
//                        loginValues.add(mModel.getLoginValue(name, pass));
//                        String jsonList = mapper.writeValueAsString(loginValues);//List转Json
//                        L.d(json);
//                        L.d(jsonList);
//                } catch (JsonProcessingException e) {
//                        e.printStackTrace();
//                }

        }

        @Override
        public void getObjectFromJson() {
//                String json = "{\"name\":\"沐白\",\"age\":23,\"birthday\":1993,\"email\":\"mubai@sina.com\"}";
//                /**
//                 * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
//                 */
//                ObjectMapper mapper = new ObjectMapper();
//                try {
//                        LoginValue user = mapper.readValue(json, LoginValue.class);
//                        L.d(user.getEmail());
//                } catch (IOException e) {
//                        e.printStackTrace();
//                }
        }

        public void login_1(String name, String pass) {
                userManager.performLogin(name, pass);
        }

        public void login_2(String username, String password) {
                if (username == null || username.length() == 0) return;
                //假设我们对密码强度有一定要求，使用一个专门的validator来验证密码的有效性
                if (passwordValidator.verifyPassword(password)) return;//这边可能是个耗时操作

                userManager.performLogin(null, password);
        }
}
