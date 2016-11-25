package com.mob.mubai;

import com.mob.mubai.data.model.PasswordValidator;
import com.mob.mubai.base.utils.UserManager;
import com.mob.mubai.ui.presenter.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by mubai on 2016/11/3.
 */

public class LoginUnitTest {

        LoginPresenter loginPresenter;
        UserManager mockUserManager;
        PasswordValidator passwordValidator;


        @Before
        public void setUp() {
                mockUserManager = Mockito.mock(UserManager.class);
                passwordValidator = Mockito.mock(PasswordValidator.class);
                loginPresenter = new LoginPresenter(mockUserManager, passwordValidator);
        }

        @Test
        public void loginTest() throws Exception {
                loginPresenter.login_1("name", "pass");
                Mockito.verify(mockUserManager, Mockito.times(1)).performLogin("name", "pass");
                Mockito.when(passwordValidator.verifyPassword("pass")).thenReturn(true);
                Mockito.verify(mockUserManager, Mockito.times(1)).performLogin(Mockito.anyString(), Mockito.anyString());
        }
}
