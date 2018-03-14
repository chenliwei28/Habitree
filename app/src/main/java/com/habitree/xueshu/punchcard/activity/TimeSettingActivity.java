package com.habitree.xueshu.punchcard.activity;


import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomRadioGroup;

public class TimeSettingActivity extends BaseActivity implements OnClickListener{

    private TextView mConfirmTv;
    private CustomRadioGroup mDaysCrg;
    private int mDays;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_time_setting;
    }

    @Override
    protected void initView() {
        mConfirmTv = findViewById(R.id.confirm_tv);
        mDaysCrg = findViewById(R.id.days_crg);
    }

    @Override
    protected void initListener() {
        mConfirmTv.setOnClickListener(this);
        mDaysCrg.setOnCheckedChangeListener(new CustomRadioGroup.OnCheckChangeListener() {
            @Override
            public void onCheckChange(RadioButton button) {
                switch (button.getId()){
                    case R.id.seven:
                        mDays = 7;
                        break;
                    case R.id.fifteen:
                        mDays = 15;
                        break;
                    case R.id.thirty:
                        mDays = 30;
                        break;
                    case R.id.sixty:
                        mDays = 60;
                        break;
                    case R.id.ninety:
                        mDays = 90;
                        break;
                    case R.id.hundred:
                        mDays = 100;
                        break;
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
            case R.id.confirm_tv:
                if (mDays==0){
                    showToast(getString(R.string.please_choose_recycle_days));
                }else {
                    setResult(Constant.NUM_110,new Intent(TimeSettingActivity.this,HabitSettingActivity.class).putExtra(Constant.POSITION,mDays));
                    AppManager.getAppManager().finishActivity(TimeSettingActivity.this);
                }
                break;
        }
    }
}
