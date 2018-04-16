package com.habitree.xueshu.mine.activity;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.Wallet;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.view.CustomItemView;

/**
 * 我的钱包
 */
public class MyWalletActivity extends BaseActionBarActivity implements View.OnClickListener, MyView.MyWalletView {

    // 总金额|账户余额|习惯金额|冻结金额
    private TextView mTotalTv, mBalanceTv, mHabitTv, mFreezeTv;
    private CustomItemView mTopUpCiv;
    private CustomItemView mWithdrawCiv;
    private CustomItemView mTransactionRecordCiv;
    private CustomItemView mPaymentRecordCiv;
    private MyPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void initView() {
        mHabitTv = findViewById(R.id.habit_tv);
        mTotalTv = findViewById(R.id.total_tv);
        mFreezeTv = findViewById(R.id.freeze_tv);
        mTopUpCiv = findViewById(R.id.top_up_civ);
        mWithdrawCiv = findViewById(R.id.withdraw_civ);
        mBalanceTv = findViewById(R.id.balance_tv);
        mTransactionRecordCiv = findViewById(R.id.transaction_record_civ);
        mPaymentRecordCiv = findViewById(R.id.payment_record_civ);
        mPresenter = new MyPresenter(this);
    }

    @Override
    protected void initListener() {
        mTopUpCiv.setOnClickListener(this);
        mWithdrawCiv.setOnClickListener(this);
        mTransactionRecordCiv.setOnClickListener(this);
        mPaymentRecordCiv.setOnClickListener(this);
        mTotalTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.my_wallet);
    }

    private void updateData() {
        showLoadingDialog();
        mPresenter.getMyWalletInfo(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_up_civ:
                startActivity(new Intent(this, TopUpActivity.class));
                break;
            case R.id.withdraw_civ:
                startActivity(new Intent(this, WithdrawActivity.class));
                break;
            case R.id.transaction_record_civ:
                startActivity(new Intent(this, TransactionRecordActivity.class));
                break;
            case R.id.payment_record_civ:
                startActivity(new Intent(this, PaymentRecordActivity.class));
                break;
            case R.id.total_tv:
                startActivity(new Intent(this, TotalMoneyActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    @Override
    public void onWalletInfoGetSuccess(Wallet wallet) {
        hideLoadingDialog();

        mTotalTv.setText("¥ " + wallet.sum_money);

        String balance = getBanlnceValue(wallet);
        mBalanceTv.setText(balance);

        String freeze = getFrozenValue(wallet);
        mFreezeTv.setText(freeze);

        String habit = getHabitValue(wallet);
        mHabitTv.setText(habit);
    }

    /**
     * 获取习惯金额
     *
     * @param wallet
     * @return
     */
    public String getHabitValue(Wallet wallet) {
        try {
            float total = Float.valueOf(wallet.sum_money);
            float balance = Float.valueOf(wallet.balance);
            float frozen = Float.valueOf(wallet.frozen_money);
            int habit = (int) (total - balance - frozen);
            return "¥ " + habit;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "¥ 0";
    }

    /**
     * 获取账户余额
     *
     * @param wallet
     * @return
     */
    public String getBanlnceValue(Wallet wallet) {
        try {
            float balance = Float.valueOf(wallet.balance);
            return "¥ " + (int) balance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "¥ 0";
    }

    /**
     * 获取冻结金额
     *
     * @param wallet
     * @return
     */
    public String getFrozenValue(Wallet wallet) {
        try {
            float frozen = Float.valueOf(wallet.frozen_money);
            return "¥ " + (int) frozen;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "¥ 0";
    }

    @Override
    public void onWalletInfoGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
