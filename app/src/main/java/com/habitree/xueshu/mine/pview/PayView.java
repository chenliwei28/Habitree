package com.habitree.xueshu.mine.pview;

import com.habitree.xueshu.punchcard.bean.PayResultResponse;
import com.habitree.xueshu.mine.bean.PayWayResponse;
import com.habitree.xueshu.xs.presenter.BaseView;

import java.util.List;


public interface PayView extends BaseView {
    void onPaySuccess(PayResultResponse.Data data);
    void onPayFailed(String reason);

    interface PayWayView{
        void onPayWayGetSuccess(List<PayWayResponse.Payway> list);
        void onPayWayGetFailed(String reason);
    }

    interface WithdrawView {
        void onWithdrawSuccess();
        void onWithdrawFailed(String reason);
    }
}
