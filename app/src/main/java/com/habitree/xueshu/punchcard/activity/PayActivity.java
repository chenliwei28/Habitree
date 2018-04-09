package com.habitree.xueshu.punchcard.activity;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.activity.WxPayActivity;
import com.habitree.xueshu.mine.presenter.PayPresenter;
import com.habitree.xueshu.mine.pview.PayView;
import com.habitree.xueshu.punchcard.bean.PayResultResponse;
import com.habitree.xueshu.mine.bean.PayWayResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.CustomItemView;

import java.util.List;

public class PayActivity extends BaseActivity implements View.OnClickListener,PayView.PayWayView,HabitView.CreateHabitView,PayView,HabitView.CreateOrderView {

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
//                    toPay(mAli.payname);
                    break;
            }
        }
    }

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

    @Override
    public void onPaySuccess(PayResultResponse.Data data) {
        if (data!=null&&data.token!=null&&!TextUtils.isEmpty(data.token)){
            WxPayActivity.start(this,data.token);
        }else {
            mHabitPresenter.createHabit(this);
        }
    }

    @Override
    public void onPayFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

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
                    onPaySuccess(null);
                }
                break;
        }
    }
}
