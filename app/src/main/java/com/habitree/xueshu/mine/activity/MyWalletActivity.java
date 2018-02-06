package com.habitree.xueshu.mine.activity;


import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.CustomItemView;

public class MyWalletActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTopUpTv;
    private TextView mWithdrawTv;
    private CustomItemView mTransactionRecordCiv;
    private CustomItemView mPaymentRecordCiv;

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
        mTopUpTv = findViewById(R.id.top_up_tv);
        mWithdrawTv = findViewById(R.id.withdraw_tv);
        mTransactionRecordCiv = findViewById(R.id.transaction_record_civ);
        mPaymentRecordCiv = findViewById(R.id.payment_record_civ);
    }

    @Override
    protected void initListener() {
        mTopUpTv.setOnClickListener(this);
        mWithdrawTv.setOnClickListener(this);
        mTransactionRecordCiv.setOnClickListener(this);
        mPaymentRecordCiv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_up_tv:
                startActivity(new Intent(this,TopUpActivity.class));
                break;
            case R.id.withdraw_tv:
                startActivity(new Intent(this,WithdrawActivity.class));
                break;
            case R.id.transaction_record_civ:
                startActivity(new Intent(this,TransactionRecordActivity.class));
                break;
            case R.id.payment_record_civ:
                startActivity(new Intent(this,PaymentRecordActivity.class));
                break;
        }
    }
}
