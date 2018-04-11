package com.habitree.xueshu.login.pview;

import com.habitree.xueshu.xs.presenter.BaseView;

public interface LoginView extends BaseView {
    void onLoginSuccess();
    void onLoginFailed(String reason);
}
