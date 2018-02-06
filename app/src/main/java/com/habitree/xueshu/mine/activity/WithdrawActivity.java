package com.habitree.xueshu.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;

//提现activity
public class WithdrawActivity extends BaseActivity implements View.OnClickListener{

    private TextView mSumTv;
    private EditText mNumEt;
    private TextView mConfirmTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected void initView() {
        mSumTv = findViewById(R.id.sum_tv);
        mNumEt = findViewById(R.id.num_et);
        mConfirmTv = findViewById(R.id.confirm_tv);
    }

    @Override
    protected void initListener() {
        mConfirmTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm_tv:

                break;
        }
    }
}
