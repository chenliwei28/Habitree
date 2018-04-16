package com.habitree.xueshu.punchcard.activity;


import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.bean.InitResponse;
import com.habitree.xueshu.xs.BaseApp;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomRadioGroup;

import java.util.List;

/**
 * 持续时间
 */
public class TimeSettingActivity extends BaseActionBarActivity implements OnClickListener{

    private TextView mConfirmTv;
    private CustomRadioGroup mDaysCrg;
    private int mDays;
    private List<InitResponse.Data.RecycleDays> mList;

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
        int[] ids = {R.id.seven,R.id.fifteen,R.id.thirty,R.id.sixty,R.id.ninety,R.id.hundred};
        mList = BaseApp.normalData.recycle_days;
        for (int i = 0;i<6;i++){
            ((RadioButton)findViewById(ids[i])).setText(mList.get(i).title);
        }
        mDaysCrg.setOnCheckedChangeListener(new CustomRadioGroup.OnCheckChangeListener() {
            @Override
            public void onCheckChange(RadioButton button) {
                switch (button.getId()){
                    case R.id.seven:
                        mDays = mList.get(0).day;
                        break;
                    case R.id.fifteen:
                        mDays = mList.get(1).day;
                        break;
                    case R.id.thirty:
                        mDays = mList.get(2).day;
                        break;
                    case R.id.sixty:
                        mDays = mList.get(3).day;
                        break;
                    case R.id.ninety:
                        mDays = mList.get(4).day;
                        break;
                    case R.id.hundred:
                        mDays = mList.get(5).day;
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        setTitle(R.string.time_of_duration);
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
