package com.habitree.xueshu.mine.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.AliPayResult;
import com.habitree.xueshu.mine.bean.PayWayResponse;
import com.habitree.xueshu.mine.presenter.PayPresenter;
import com.habitree.xueshu.mine.pview.PayView;
import com.habitree.xueshu.punchcard.bean.PayResultResponse;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;

import java.util.List;
import java.util.Map;

//充值
public class TopUpActivity extends BaseActivity implements View.OnClickListener,PayView.PayWayView,PayView{

    private EditText mNumEt;
    private LinearLayout mWxCheckLl;
    private LinearLayout mAliCheckLl;
    private ImageView mWxCheckIv;
    private ImageView mAliCheckIv;
    private TextView mNextTv;
    private PayPresenter mPayPresenter;
    private int mCurrentMode = -1;      //0：微信支付 1：支付宝支付
    private PayWayResponse.Payway mWX;
    private PayWayResponse.Payway mAli;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_top_up;
    }

    @Override
    protected void initView() {
        mNumEt = findViewById(R.id.num_et);
        mWxCheckLl = findViewById(R.id.wx_check_ll);
        mAliCheckLl = findViewById(R.id.ali_check_ll);
        mWxCheckIv = findViewById(R.id.wx_check_iv);
        mAliCheckIv = findViewById(R.id.ali_check_iv);
        mNextTv = findViewById(R.id.next_tv);
        mPayPresenter = new PayPresenter(this);
    }

    @Override
    protected void initListener() {
        mWxCheckLl.setOnClickListener(this);
        mAliCheckLl.setOnClickListener(this);
        mNextTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        showLoadingDialog();
        mPayPresenter.getPayMode(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wx_check_ll:
                selectMode(0);
                break;
            case R.id.ali_check_ll:
                selectMode(1);
                break;
            case R.id.next_tv:
                checkAndPay();
                break;
        }
    }

    private void selectMode(int position){
        if (mCurrentMode==position)return;
        mCurrentMode = position;
        switch (position){
            case 0:
                mWxCheckIv.setSelected(true);
                mAliCheckIv.setSelected(false);
                break;
            case 1:
                mWxCheckIv.setSelected(false);
                mAliCheckIv.setSelected(true);
                break;
        }
    }

    private void checkAndPay(){
        if (mCurrentMode==-1){
            showToast(getString(R.string.please_choose_pay_way));
        }else {
            switch (mCurrentMode){
                case 0:
                    toPay(mWX.payname);
                    break;
                case 1:
                    toPay(mAli.payname);
                    break;
            }
        }
    }

    private void toPay(String payWay){
        showLoadingDialog();
        mPayPresenter.topUpCreateOrder(mNumEt.getText().toString(),payWay,this);
    }

    @Override
    public void onPayWayGetSuccess(List<PayWayResponse.Payway> list) {
        initPayWay(list);
        hideLoadingDialog();
    }

    @Override
    public void onPayWayGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    private void initPayWay(List<PayWayResponse.Payway> list){
        for (PayWayResponse.Payway data:list){
            switch (data.payname){
                case "wxpay":
                    mWxCheckLl.setVisibility(data.status==2?View.VISIBLE:View.GONE);
                    mWX = data;
                    break;
                case "alipay":
                    mAliCheckLl.setVisibility(data.status==2?View.VISIBLE:View.GONE);
                    mAli = data;
                    break;
            }
        }
    }

    @Override
    public void onPaySuccess(PayResultResponse.Data data) {
        hideLoadingDialog();
        switch (mCurrentMode){
            case 0:
                WxPayActivity.start(this,data.token);
                break;
            case 1:
                mPayPresenter.startAliPay(data.order_id,"0.01",mHandler);
                break;
        }
    }

    @Override
    public void onPayFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.NUM_109:
                if (resultCode==Constant.NUM_110){
                    showToast(getString(R.string.top_up_success));
                    AppManager.getAppManager().finishActivity(this);
                }
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    @SuppressWarnings("unchecked")
                    AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showToast(getString(R.string.pay_success));
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showToast("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
}
