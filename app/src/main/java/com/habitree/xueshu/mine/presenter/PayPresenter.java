package com.habitree.xueshu.mine.presenter;


import android.content.Context;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.pview.PayView;
import com.habitree.xueshu.punchcard.bean.PayResultResponse;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayPresenter extends BasePresenter{

    public PayPresenter(Context context) {
        super(context);
    }

    public void payOrderUseBalance(String orderId, String payWay, final PayView view){
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .balancePay(timestamp, CommUtil.getSign(Constant.PAY_ORDER_FUNCTION,timestamp),
                        UserManager.getManager().getUser().user_token,orderId,payWay)
                .enqueue(new Callback<PayResultResponse>() {
                    @Override
                    public void onResponse(Call<PayResultResponse> call, Response<PayResultResponse> response) {
                        if (response.body()!=null){
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onPaySuccess();
                            }else {
                                view.onPayFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PayResultResponse> call, Throwable t) {
                        view.onPayFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    public void payOrderUseWxPay(){

    }
}
