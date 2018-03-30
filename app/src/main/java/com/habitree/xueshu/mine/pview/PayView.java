package com.habitree.xueshu.mine.pview;

import com.habitree.xueshu.xs.presenter.BaseView;



public interface PayView extends BaseView {
    void onPaySuccess();
    void onPayFailed(String reason);
}
