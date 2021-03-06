package com.habitree.xueshu.punchcard.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.activity.WxPayActivity;
import com.habitree.xueshu.mine.bean.AliPayResult;
import com.habitree.xueshu.mine.presenter.PayPresenter;
import com.habitree.xueshu.mine.pview.PayView;
import com.habitree.xueshu.punchcard.bean.PayResultResponse;
import com.habitree.xueshu.mine.bean.PayWayResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.view.CustomItemView;

import java.util.List;
import java.util.Map;

/**
 * 支付界面
 */
public class PayActivity extends BaseActionBarActivity implements View.OnClickListener,PayView.PayWayView,HabitView.CreateHabitView,PayView,HabitView.CreateOrderView ,PayView.QueryPayView{

    private LinearLayout mWxCheckLl;
    private LinearLayout mAliCheckLl;
    private LinearLayout mBalanceCheckLl;
    private ImageView mWxCheckIv;
    private ImageView mAliCheckIv;
    private ImageView mBalanceCheckIv;
    private CustomItemView mTotalCiv;
    private TextView mPayTv;
    private HabitPresenter mHabitPresenter;
    private PayPresenter mPayPresenter;
    private int mCurrentMode = -1;  //1：余额支付 2：微信支付 3：支付宝支付
    private PayWayResponse.Payway mBalance;
    private PayWayResponse.Payway mWX;
    private PayWayResponse.Payway mAli;
    private String alipayOrderID ;// 阿里支付

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        mWxCheckLl = findViewById(R.id.wx_check_ll);
        mAliCheckLl = findViewById(R.id.ali_check_ll);
        mBalanceCheckLl = findViewById(R.id.balance_check_ll);
        mWxCheckIv = findViewById(R.id.wx_check_iv);
        mAliCheckIv = findViewById(R.id.ali_check_iv);
        mBalanceCheckIv = findViewById(R.id.balance_check_iv);
        mTotalCiv = findViewById(R.id.total_civ);
        mPayTv = findViewById(R.id.pay_tv);
        mHabitPresenter = new HabitPresenter(this);
        mPayPresenter = new PayPresenter(this);
    }

    @Override
    protected void initListener() {
        mWxCheckLl.setOnClickListener(this);
        mAliCheckLl.setOnClickListener(this);
        mBalanceCheckLl.setOnClickListener(this);
        mPayTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.pay);
        showLoadingDialog();
        mHabitPresenter.initCreateHabitData(getIntent());
        mTotalCiv.setDetail("¥"+getIntent().getDoubleExtra(Constant.TOTAL,0));
        mPayPresenter.getPayMode(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.balance_check_ll:
                selectMode(1);
                break;
            case R.id.wx_check_ll:
                selectMode(2);
                break;
            case R.id.ali_check_ll:
                selectMode(3);
                break;
            case R.id.pay_tv:
                checkAndPay();
                break;
        }
    }

    private void checkAndPay(){
        if (mCurrentMode==-1){
            showToast(getString(R.string.please_choose_pay_way));
        }else {
            switch (mCurrentMode){
                case 1:
                    toPay(mBalance.payname);
                    break;
                case 2:
                    toPay(mWX.payname);
                    break;
                case 3:
                    toPay(mAli.payname);
                    break;
            }
        }
    }

    /**
     * 创建习惯惩金支付订单(预下单)
     * @param payWay
     */
    private void toPay(String payWay){
        showLoadingDialog();
        mHabitPresenter.createOrder(payWay,this);
    }

    private void selectMode(int position){
        if (mCurrentMode==position)return;
        mCurrentMode = position;
        switch (position){
            case 1:
                mWxCheckIv.setSelected(false);
                mAliCheckIv.setSelected(false);
                mBalanceCheckIv.setSelected(true);
                break;
            case 2:
                mWxCheckIv.setSelected(true);
                mAliCheckIv.setSelected(false);
                mBalanceCheckIv.setSelected(false);
                break;
            case 3:
                mWxCheckIv.setSelected(false);
                mAliCheckIv.setSelected(true);
                mBalanceCheckIv.setSelected(false);
                break;
        }
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
                case "balancepay":
                    mBalanceCheckLl.setVisibility(data.status==2?View.VISIBLE:View.GONE);
                    mBalance = data;
                    break;
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
    public void onHabitCreateSuccess() {
        hideLoadingDialog();
        HabitCreateResultActivity.start(this,getIntent().getStringExtra(Constant.HEAD),getIntent().getIntExtra(Constant.MEMID,0),
                getIntent().getStringExtra(Constant.TITLE));
    }

    @Override
    public void onHabitCreateFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    /**
     * 习惯预下单支付
     * @param data
     */
    @Override
    public void onPaySuccess(PayResultResponse.Data data) {
        switch (mCurrentMode){
            case 1:
                mHabitPresenter.createHabit(this);
                break;
            case 2:
                if (data!=null&&data.token!=null&&!TextUtils.isEmpty(data.token)){
                    WxPayActivity.start(this,data.token);
                }
                break;
            case 3:
                // 阿里支付
                mPayPresenter.startAliPay(data.order_id,String.valueOf(data.amount),getString(R.string.habit_create),mHandler);
                break;
        }
    }

    @Override
    public void onPayFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    /**
     * 创建习惯惩金支付订单(预下单) 返回结果
     * @param orderId
     */
    @Override
    public void onOrderCreateSuccess(String orderId) {
        switch (mCurrentMode){
            case 1:
                mPayPresenter.payOrder(orderId,mBalance.payname,this);
                break;
            case 2:
                mPayPresenter.payOrder(orderId,mWX.payname,this);
                break;
            case 3:
                this.alipayOrderID = orderId;
                mPayPresenter.payOrder(orderId,mAli.payname,this);
                break;
        }
    }

    @Override
    public void onOrderCreateFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constant.NUM_109:
                if (resultCode==Constant.NUM_110){
                    mHabitPresenter.createHabit(this);
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
//                        mHabitPresenter.createHabit(PayActivity.this);

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        showToast("支付失败");
                    }
                    if(!TextUtils.isEmpty(alipayOrderID)){
                        mPayPresenter.queryOrderStateByAliPay(alipayOrderID, PayActivity.this);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };


    /**
     * 查询订单
     * @param orderId
     */
    private void query(final String orderId){
        mPayTv.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPayPresenter.queryOrderStateByAliPay(orderId, PayActivity.this);
            }
        },2000);
    }

    @Override
    public void onQueryAliPaySuccess() {
        // 阿里支付成功
        mHabitPresenter.createHabit(PayActivity.this);
        showToast("支付成功");
    }

    @Override
    public void onQueryAliPayFailed(String reason) {
        query(alipayOrderID);
    }
}
