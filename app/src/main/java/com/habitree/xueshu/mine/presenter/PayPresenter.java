package com.habitree.xueshu.mine.presenter;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.QueryOrderResponse;
import com.habitree.xueshu.mine.bean.TopUpOrderResponse;
import com.habitree.xueshu.mine.bean.WithdrawOrderResponse;
import com.habitree.xueshu.mine.pview.PayView;
import com.habitree.xueshu.punchcard.bean.PayResultResponse;
import com.habitree.xueshu.mine.bean.PayWayResponse;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.presenter.BasePresenter;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.HttpManager;
import com.habitree.xueshu.xs.util.LogUtil;
import com.habitree.xueshu.xs.util.OrderInfoUtil2_0;
import com.habitree.xueshu.xs.util.TimeUtil;
import com.habitree.xueshu.xs.util.UserManager;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.habitree.xueshu.xs.Constant.RSA2_PRIVATE;
import static com.habitree.xueshu.xs.Constant.RSA_PRIVATE;


public class PayPresenter extends BasePresenter {

    public PayPresenter(Context context) {
        super(context);
    }

    //获取支付方式
    public void getPayMode(final PayView.PayWayView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .getPayWay(timestamp, CommUtil.getSign(Constant.GET_PAYWAY_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token)
                .enqueue(new Callback<PayWayResponse>() {
                    @Override
                    public void onResponse(Call<PayWayResponse> call, Response<PayWayResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onPayWayGetSuccess(response.body().data);
                            } else {
                                view.onPayWayGetFailed(mContext.getString(R.string.network_error));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PayWayResponse> call, Throwable t) {
                        view.onPayWayGetFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    //习惯支付
    public void payOrder(String orderId, String payWay, final PayView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService()
                .balancePay(timestamp, CommUtil.getSign(Constant.PAY_ORDER_FUNCTION, timestamp),
                        UserManager.getManager().getUser().user_token, orderId, payWay)
                .enqueue(new Callback<PayResultResponse>() {
                    @Override
                    public void onResponse(Call<PayResultResponse> call, Response<PayResultResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onPaySuccess(response.body().data);
                            } else {
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

    //查询习惯支付订单状态，主要是微信
    public void queryOrderState(String orderId, final PayView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().queryOrderState(timestamp, CommUtil.getSign(Constant.QUERY_ORDER_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, orderId)
                .enqueue(new Callback<QueryOrderResponse>() {
                    @Override
                    public void onResponse(Call<QueryOrderResponse> call, Response<QueryOrderResponse> response) {
                        if (response.body() != null) {
                            QueryOrderResponse res = response.body();
                            if (CommUtil.isSuccess(mContext, res.status)) {
                                LogUtil.d("order status is:" + res.data.status);
                                if (res.data.status == 2) {
                                    view.onPaySuccess(null);
                                } else {
                                    view.onPayFailed(null);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<QueryOrderResponse> call, Throwable t) {
                        view.onPayFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    //提现预下单
    public void withdrawCreateOrder(String amount, final PayView.WithdrawView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().withdrawCreateOrder(timestamp, CommUtil.getSign(Constant.WITHDRAW_CREATE_ORDER_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, amount)
                .enqueue(new Callback<WithdrawOrderResponse>() {
                    @Override
                    public void onResponse(Call<WithdrawOrderResponse> call, Response<WithdrawOrderResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext,response.body().status)){
                                view.onWithdrawSuccess();
                            }else {
                                view.onWithdrawFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<WithdrawOrderResponse> call, Throwable t) {
                        view.onWithdrawFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    //充值预下单
    public void topUpCreateOrder(String amount, final String payway, final PayView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().topUpCreateOrder(timestamp, CommUtil.getSign(Constant.TOP_UP_CREATE_ORDER_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, amount, payway)
                .enqueue(new Callback<TopUpOrderResponse>() {
                    @Override
                    public void onResponse(Call<TopUpOrderResponse> call, Response<TopUpOrderResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                topUpPay(response.body().data.order_id, payway, view);
                            } else {
                                view.onPayFailed(CommUtil.unicode2Chinese(response.body().info));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TopUpOrderResponse> call, Throwable t) {
                        view.onPayFailed(mContext.getString(R.string.network_error));
                    }
                });
    }

    //充值预下单支付
    private void topUpPay(String orderId, String payway, final PayView view) {
        String timestamp = String.valueOf(TimeUtil.getCurrentMillis());
        HttpManager.getManager().getService().topUpPay(timestamp, CommUtil.getSign(Constant.TOP_UP_PAY_FUNCTION, timestamp),
                UserManager.getManager().getUser().user_token, orderId, payway)
                .enqueue(new Callback<PayResultResponse>() {
                    @Override
                    public void onResponse(Call<PayResultResponse> call, Response<PayResultResponse> response) {
                        if (response.body() != null) {
                            if (CommUtil.isSuccess(mContext, response.body().status)) {
                                view.onPaySuccess(response.body().data);
                            } else {
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

    public void startAliPay(String orderId, String amount,String title,final Handler handler) {
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Constant.ALI_PAY_APP_ID, orderId,amount,title,rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) mContext);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                LogUtil.d("str is:"+result.toString());
                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
