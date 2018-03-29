package com.habitree.xueshu.mine.activity;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.bean.Wallet;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.CustomItemView;

public class MyWalletActivity extends BaseActivity implements View.OnClickListener,MyView.MyWalletView {

    private TextView mBalanceTv;
    private TextView mTotalTv;
    private TextView mFreezeTv;
    private LinearLayout mTotalLl;
    private LinearLayout mFreezeLl;
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
    protected void initStatusBar() {
        UIUtil.setStatusBar(this,getResources().getColor(R.color.trans));
    }

    @Override
    protected void initView() {
        mTotalTv = findViewById(R.id.total_tv);
        mFreezeTv = findViewById(R.id.freeze_tv);
        mTopUpCiv = findViewById(R.id.top_up_civ);
        mTotalLl = findViewById(R.id.total_ll);
        mFreezeLl = findViewById(R.id.freeze_ll);
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
        mTotalLl.setOnClickListener(this);
        mFreezeLl.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    private void updateData(){
        showLoadingDialog();
        mPresenter.getMyWalletInfo(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_up_civ:
                startActivity(new Intent(this,TopUpActivity.class));
                break;
            case R.id.withdraw_civ:
                startActivity(new Intent(this,WithdrawActivity.class));
                break;
            case R.id.transaction_record_civ:
                startActivity(new Intent(this,TransactionRecordActivity.class));
                break;
            case R.id.payment_record_civ:
                startActivity(new Intent(this,PaymentRecordActivity.class));
                break;
            case R.id.total_ll:
                startActivity(new Intent(this,TotalMoneyActivity.class));
                break;
            case R.id.freeze_ll:

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
        mBalanceTv.setText(wallet.balance);
        String total = "¥ "+wallet.sum_money;
        mTotalTv.setText(total);
        String freeze = "¥ "+wallet.frozen_money;
        mFreezeTv.setText(freeze);
    }

    @Override
    public void onWalletInfoGetFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
