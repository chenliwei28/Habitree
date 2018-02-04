package com.habitree.xueshu.punchcard.activity;


import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.CustomRadioGroup;

public class ForfeitSettingActivity extends BaseActivity implements View.OnClickListener{

    private CustomRadioGroup mNumCrg;
    private EditText mSumEt;
    private TextView mNumTv;
    private TextView mPayTv;

    private int mTotalTimes = 30;
    private int mTotalMoney;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_forfeit_setting;
    }

    @Override
    protected void initView() {
        mNumCrg = findViewById(R.id.num_crg);
        mSumEt = findViewById(R.id.sum_et);
        mNumTv = findViewById(R.id.num_tv);
        mPayTv = findViewById(R.id.pay_tv);
    }

    @Override
    protected void initListener() {
        mPayTv.setOnClickListener(this);
        mNumCrg.setOnCheckedChangeListener(new CustomRadioGroup.OnCheckChangeListener() {
            @Override
            public void onCheckChange(RadioButton button) {
                if (!TextUtils.isEmpty(mSumEt.getText().toString()))mSumEt.setText("");
                switch (button.getId()){
                    case R.id.one:
                        mTotalMoney = mTotalTimes;
                        break;
                    case R.id.five:
                        mTotalMoney = mTotalTimes*5;
                        break;
                    case R.id.ten:
                        mTotalMoney = mTotalTimes*10;
                        break;
                    case R.id.fifteen:
                        mTotalMoney = mTotalTimes*15;
                        break;
                    case R.id.twenty:
                        mTotalMoney = mTotalTimes*20;
                        break;
                    case R.id.fifty:
                        mTotalMoney = mTotalTimes*50;
                        break;
                }
                mNumTv.setText(String.format(getString(R.string.summation_money),mTotalMoney));
            }
        });
        mSumEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString();
                if (!TextUtils.isEmpty(s)){
                    mNumCrg.clearCheck();
                    mTotalMoney = mTotalTimes*Integer.valueOf(s);
                    mNumTv.setText(String.format(getString(R.string.summation_money),mTotalMoney));
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pay_tv:
                startActivity(new Intent(ForfeitSettingActivity.this,PayActivity.class));
                break;
        }
    }
}
