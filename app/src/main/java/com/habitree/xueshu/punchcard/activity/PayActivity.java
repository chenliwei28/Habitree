package com.habitree.xueshu.punchcard.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;

public class PayActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout mWxCheckLl;
    private LinearLayout mAliCheckLl;
    private LinearLayout mBalanceCheckLl;
    private ImageView mWxCheckIv;
    private ImageView mAliCheckIv;
    private ImageView mBalanceCheckIv;

    private int mCurrentMode = -1;

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
    }

    @Override
    protected void initListener() {
        mWxCheckLl.setOnClickListener(this);
        mAliCheckLl.setOnClickListener(this);
        mBalanceCheckLl.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        selectMode(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wx_check_ll:
                selectMode(0);
                break;
            case R.id.ali_check_ll:
                selectMode(1);
                break;
            case R.id.balance_check_ll:
                selectMode(2);
                break;
        }
    }

    private void selectMode(int position){
        if (mCurrentMode==position)return;
        mCurrentMode = position;
        switch (position){
            case 0:
                mWxCheckIv.setSelected(true);
                mAliCheckIv.setSelected(false);
                mBalanceCheckIv.setSelected(false);
                break;
            case 1:
                mWxCheckIv.setSelected(false);
                mAliCheckIv.setSelected(true);
                mBalanceCheckIv.setSelected(false);
                break;
            case 2:
                mWxCheckIv.setSelected(false);
                mAliCheckIv.setSelected(false);
                mBalanceCheckIv.setSelected(true);
                break;
        }
    }
}
