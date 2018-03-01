package com.habitree.xueshu.login.pview;

import com.habitree.xueshu.login.bean.User;
import com.habitree.xueshu.xs.presenter.BaseView;



public interface LoginView extends BaseView {
    void onLoginSuccess(User user);
    void onLoginFailed(String reason);
}
