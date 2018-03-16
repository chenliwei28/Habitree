package com.habitree.xueshu.punchcard.activity;


import android.app.Activity;
import android.content.Context;
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
import com.habitree.xueshu.punchcard.bean.InitResponse;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomRadioGroup;

import java.util.List;

public class ForfeitSettingActivity extends BaseActivity implements View.OnClickListener{

    private CustomRadioGroup mNumCrg;
    private EditText mSumEt;
    private TextView mNumTv;
    private TextView mPayTv;
    private TextView mNoteTv;

    private int mTotalTimes;
    private double mTotalMoney;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_forfeit_setting;
    }

    public static void start(Activity context, int totalTimes, int requestCode){
        Intent intent = new Intent(context,ForfeitSettingActivity.class);
        intent.putExtra(Constant.RECYCLE,totalTimes);
        context.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void initView() {
        mNumCrg = findViewById(R.id.num_crg);
        mSumEt = findViewById(R.id.sum_et);
        mNumTv = findViewById(R.id.num_tv);
        mPayTv = findViewById(R.id.pay_tv);
        mNoteTv = findViewById(R.id.note_tv);
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
                        mTotalMoney = mTotalTimes*BaseApp.normalData.config.pay_rate;
                        break;
                    case R.id.five:
                        mTotalMoney = mTotalTimes*5*BaseApp.normalData.config.pay_rate;
                        break;
                    case R.id.ten:
                        mTotalMoney = mTotalTimes*10*BaseApp.normalData.config.pay_rate;
                        break;
                    case R.id.fifteen:
                        mTotalMoney = mTotalTimes*15*BaseApp.normalData.config.pay_rate;
                        break;
                    case R.id.twenty:
                        mTotalMoney = mTotalTimes*20*BaseApp.normalData.config.pay_rate;
                        break;
                    case R.id.fifty:
                        mTotalMoney = mTotalTimes*50*BaseApp.normalData.config.pay_rate;
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
                    mTotalMoney = mTotalTimes*Integer.valueOf(s)*BaseApp.normalData.config.pay_rate;
                    mNumTv.setText(String.format(getString(R.string.summation_money),mTotalMoney));
                }
            }
        });
    }

    @Override
    protected void initData() {
        mTotalTimes = getIntent().getIntExtra(Constant.RECYCLE,0);
        mNoteTv.setText(String.format(getString(R.string.forfeit_setting_long_text),mTotalMoney));
        mNumTv.setText(String.format(getString(R.string.summation_money),mTotalMoney));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pay_tv:
                if (mTotalMoney==0){
                    showToast(getString(R.string.please_choose_price));
                }else {
                    int per = (int) (mTotalMoney/(mTotalTimes*BaseApp.normalData.config.pay_rate));
                    Intent intent = new Intent(ForfeitSettingActivity.this,SupervisionSettingActivity.class);
                    intent.putExtra(Constant.NUMBER,mTotalMoney);
                    intent.putExtra(Constant.POSITION,per);
                    setResult(Constant.NUM_110,intent);
                    AppManager.getAppManager().finishActivity(ForfeitSettingActivity.this);
                }
                break;
        }
    }
}
