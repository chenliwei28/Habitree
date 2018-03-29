package com.habitree.xueshu.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;

public class TotalMoneyActivity extends BaseActivity {

    private TextView mTotalTv;
    private TextView mFreezeTv;
    private TextView mBalanceTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_total_money;
    }

    @Override
    protected void initView() {
        mTotalTv = findViewById(R.id.total_tv);
        mFreezeTv = findViewById(R.id.freeze_tv);
        mBalanceTv = findViewById(R.id.balance_tv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mTotalTv.setText(UserManager.getManager().getUser().wallet.sum_money);
        mFreezeTv.setText(UserManager.getManager().getUser().wallet.frozen_money);
        mBalanceTv.setText(UserManager.getManager().getUser().wallet.balance);
    }

    @Override
    protected void initStatusBar() {
        UIUtil.setStatusBar(this,getResources().getColor(R.color.trans));
    }
}
