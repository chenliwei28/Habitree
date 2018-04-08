package com.habitree.xueshu.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.ChargeDetailResponse;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.CustomItemView;

public class TransactionDetailActivity extends BaseActivity implements MyView.ChargeDetailView{

    private CustomItemView mNumCiv;
    private CustomItemView mTypeCiv;
    private CustomItemView mSpendCiv;
    private CustomItemView mMethodCiv;
    private CustomItemView mCycleCiv;
    private CustomItemView mSingleCiv;
    private CustomItemView mTimeCiv;
    private CustomItemView mBalanceCiv;
    private CustomItemView mStatusCiv;
    private MyPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_transaction_detail;
    }

    public static void start(Context context,String orderId){
        context.startActivity(new Intent(context,TransactionDetailActivity.class).putExtra(Constant.ID,orderId));
    }

    @Override
    protected void initView() {
        mNumCiv = findViewById(R.id.num_civ);
        mTypeCiv = findViewById(R.id.type_civ);
        mSpendCiv = findViewById(R.id.spend_civ);
        mMethodCiv = findViewById(R.id.method_civ);
        mCycleCiv = findViewById(R.id.cycle_civ);
        mSingleCiv = findViewById(R.id.single_civ);
        mTimeCiv = findViewById(R.id.time_civ);
        mBalanceCiv = findViewById(R.id.balance_civ);
        mStatusCiv = findViewById(R.id.status_civ);
        mPresenter = new MyPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.getChargeDetail(getIntent().getStringExtra(Constant.ID),this);
    }

    @Override
    public void onDetailGetSuccess(ChargeDetailResponse.DataBean bean) {
        switch (bean.trade_type){
            case "1":
                showTopUp(bean);
                break;
            case "2":
                showWithdraw(bean);
                break;
            case "3":
                showHabitPay(bean);
                break;
            case "4":
                showForfeit(bean);
                break;
        }
    }

    @Override
    public void onDetailGetFailed(String reason) {
        showToast(reason);
    }

    private void showWithdraw(ChargeDetailResponse.DataBean bean){
        mNumCiv.setDetail(bean.order_id);
        mTypeCiv.setDetail(bean.trade_str);
        mSpendCiv.setDetail(bean.amount);
        mMethodCiv.setDetail(bean.info_title);
        mTimeCiv.setDetail(bean.create_time);
        mTimeCiv.setTitleText(R.string.withdraw_time);
        mStatusCiv.setDetail(bean.status_str);
        mBalanceCiv.setDetail(UserManager.getManager().getUser().wallet.balance);
    }

    private void showTopUp(ChargeDetailResponse.DataBean bean){
        mNumCiv.setDetail(bean.order_id);
        mTypeCiv.setDetail(bean.trade_str);
        mSpendCiv.setDetail(bean.amount);
        mMethodCiv.setDetail(bean.info_title);
        mTimeCiv.setDetail(bean.create_time);
        mTimeCiv.setTitleText(R.string.top_up_time);
        mStatusCiv.setDetail(bean.status_str);
        mBalanceCiv.setDetail(UserManager.getManager().getUser().wallet.balance);
    }

    private void showHabitPay(ChargeDetailResponse.DataBean bean){
        mNumCiv.setDetail(bean.order_id);
        mTypeCiv.setTitleText(R.string.habit_name_mao);
        mTypeCiv.setDetail(bean.trade_str);
        mSpendCiv.setTitleText(R.string.habit_pay);
        mSpendCiv.setDetail(bean.amount);
        mMethodCiv.setDetail(bean.info_title);
        mTimeCiv.setDetail(bean.create_time);
        mStatusCiv.setDetail(bean.status_str);
        mCycleCiv.setVisibility(View.VISIBLE);
        mCycleCiv.setDetail(String.format(getString(R.string.num_days),bean.habit_info.recycle_days));
        mSingleCiv.setVisibility(View.VISIBLE);
        mSingleCiv.setDetail(bean.habit_info.unit_price+getString(R.string.yuan_every_time));
        mBalanceCiv.setDetail(UserManager.getManager().getUser().wallet.balance);
    }

    private void showForfeit(ChargeDetailResponse.DataBean bean){
        mNumCiv.setDetail(bean.order_id);
        mTypeCiv.setTitleText(R.string.habit_name_mao);
        mTypeCiv.setDetail(bean.trade_str);
        mSpendCiv.setTitleText(R.string.returns_amount);
        mSpendCiv.setDetail(bean.amount);
        mTimeCiv.setDetail(bean.create_time);
        mStatusCiv.setDetail(bean.status_str);
        mCycleCiv.setVisibility(View.VISIBLE);
        mCycleCiv.setDetail(String.format(getString(R.string.num_days),bean.habit_info.recycle_days));
        mSingleCiv.setVisibility(View.VISIBLE);
        mSingleCiv.setTitleText(R.string.habit_days);
        mSingleCiv.setDetail("第"+bean.habit_info.now_days+"天");
        mBalanceCiv.setDetail(UserManager.getManager().getUser().wallet.balance);
    }
}
