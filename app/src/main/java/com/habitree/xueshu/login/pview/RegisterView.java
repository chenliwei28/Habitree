package com.habitree.xueshu.login.pview;

import com.habitree.xueshu.xs.presenter.BaseView;



public interface RegisterView extends BaseView {
    interface AuthCodeView{
        void onSendSuccess();
        void onSendFail();
    }
}
