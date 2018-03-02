package com.habitree.xueshu.login.pview;

import com.habitree.xueshu.xs.presenter.BaseView;



public interface RegisterView extends BaseView {
    void onRegisterSuccess();
    void onRegisterFail(String reason);

    interface AuthCodeView{
        void onSendSuccess();
        void onSendFail(String reason);
    }

    interface ChangeBindView{
        void onChangeSuccess();
        void onChangeFail(String reason);
    }
}
