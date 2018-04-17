package com.habitree.xueshu.mine.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.presenter.PayPresenter;
import com.habitree.xueshu.mine.pview.PayView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;

/**
 * 提现activity
 */
public class WithdrawActivity extends BaseActionBarActivity implements View.OnClickListener, PayView.WithdrawView {

    private TextView mNameTv;
    private TextView mAccountTv;
    private EditText mNumEt;
    private TextView mBalanceTv;
    private TextView mAllTv;
    private TextView mConfirmTv;
    private PayPresenter mPresenter;
    private String mAmount;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {
        mNameTv = findViewById(R.id.name_tv);
        mAccountTv = findViewById(R.id.account_tv);
        mNumEt = findViewById(R.id.num_et);
        mBalanceTv = findViewById(R.id.balance_tv);
        mAllTv = findViewById(R.id.all_tv);
        mConfirmTv = findViewById(R.id.confirm_tv);
        mPresenter = new PayPresenter(this);
    }

    @Override
    protected void initListener() {
        setTitle(R.string.withdraw_deposit);
        mAllTv.setOnClickListener(this);
        mConfirmTv.setOnClickListener(this);
    }

    private int oAuthId = -1;

    @Override
    protected void initData() {
        oAuthId = getIntent().getIntExtra(Constant.OAUTH_ID,-1);
        String ba = "可用余额：" + UserManager.getManager().getUser().wallet.balance + "元";
        mBalanceTv.setText(ba);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_tv:
                mNumEt.setText(UserManager.getManager().getUser().wallet.balance);
                break;
            case R.id.confirm_tv:
                checkAndConfirm();
                break;
        }
    }

    private void checkAndConfirm() {
        mAmount = mNumEt.getText().toString();
        double amount = Double.valueOf(mAmount);
        if (TextUtils.isEmpty(mAmount)) {
            showToast("提现金额不能为空");
        } else if (amount <= 0) {
            showToast("提现金额不能为0");
        } else {
            showLoadingDialog();
            if(oAuthId != -1){
                mPresenter.withdrawCreateOrder(mAmount,oAuthId, this);
            }
        }
    }

    @Override
    public void onWithdrawSuccess() {
        hideLoadingDialog();
        Intent intent = new Intent(WithdrawActivity.this, WithDrawSuccessActivity.class);
        intent.putExtra(Constant.MONEY_VALUE,mNumEt.getText().toString());
        UIUtil.startActivity(WithdrawActivity.this,intent);
        AppManager.getAppManager().finishActivity(WithdrawActivity.this);
    }

    @Override
    public void onWithdrawFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
