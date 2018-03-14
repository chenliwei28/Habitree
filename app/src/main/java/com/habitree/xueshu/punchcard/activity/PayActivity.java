package com.habitree.xueshu.punchcard.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.bean.CreateOrderResponse;
import com.habitree.xueshu.punchcard.bean.PayWayResponse;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.CustomItemView;

import java.util.List;

public class PayActivity extends BaseActivity implements View.OnClickListener,HabitView.PayWayView,HabitView.CreateOrderView{

    private LinearLayout mWxCheckLl;
    private LinearLayout mAliCheckLl;
    private LinearLayout mBalanceCheckLl;
    private ImageView mWxCheckIv;
    private ImageView mAliCheckIv;
    private ImageView mBalanceCheckIv;
    private CustomItemView mTotalCiv;
    private TextView mPayTv;
    private HabitPresenter mPresenter;
    private int mCurrentMode = -1;  //1：余额支付 2：微信支付 3：支付宝支付
    private PayWayResponse.Payway mBalance;
    private PayWayResponse.Payway mWX;
    private PayWayResponse.Payway mAli;

    private int mTreeId;        //树ID
    private String mDescribe;   //习惯描述
    private int mRemindTime;    //提醒时间
    private String mRepeatDays; //重复日期
    private int mRecycleDays;   //习惯天数
    private int mPrivacyType;   //隐私模式
    private int mRecordType;    //记录方式
    private int mPerMoney;      //罚金单价
    private int mTotalMoney;    //罚金总价
    private String mOrderId;       //订单ID

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
        mPresenter = new HabitPresenter(this);
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
//        showLoadingDialog();
        mTreeId = getIntent().getIntExtra(Constant.ID,0);
        mDescribe = getIntent().getStringExtra(Constant.TITLE);
        mRemindTime = getIntent().getIntExtra(Constant.REMIND,0);
        mRepeatDays = getIntent().getStringExtra(Constant.REPEAT);
        mRecycleDays = getIntent().getIntExtra(Constant.RECYCLE,0);
        mPrivacyType = getIntent().getIntExtra(Constant.PRIVACY,0);
        mRecordType = getIntent().getIntExtra(Constant.RECORD,0);
        mTotalMoney = getIntent().getIntExtra(Constant.TOTAL,0);
        mPerMoney = getIntent().getIntExtra(Constant.PER,0);
        mTotalCiv.setDetail("¥"+mTotalMoney);
        mPresenter.getPayMode(this);
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
                    useBalancePay();
                    break;
                case 2:
                    showToast("暂不支持微信支付哦");
                    break;
                case 3:
                    showToast("暂不支持支付宝哦");
                    break;
            }
        }
    }

    private void useBalancePay(){
        showLoadingDialog();
        mPresenter.createOrder(mTotalMoney,mBalance.payname,mDescribe,this);
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
        hideLoadingDialog();
        initPayWay(list);
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
    public void onOrderCreateSuccess(CreateOrderResponse.Order order) {
        mOrderId = order.order_id;
        switch (mCurrentMode){
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
    }

    @Override
    public void onOrderCreateFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
