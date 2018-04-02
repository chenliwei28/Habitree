package com.habitree.xueshu.mine.pview;

import com.habitree.xueshu.punchcard.bean.PayResultResponse;
import com.habitree.xueshu.xs.presenter.BaseView;



public interface PayView extends BaseView {
    void onPaySuccess(PayResultResponse.Data data);
    void onPayFailed(String reason);
}
