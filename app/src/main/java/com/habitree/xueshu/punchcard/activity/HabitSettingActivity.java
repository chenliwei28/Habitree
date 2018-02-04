package com.habitree.xueshu.punchcard.activity;



import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;

public class HabitSettingActivity extends BaseActivity implements View.OnClickListener{

    private MyActionBar mSettingMab;
    private EditText mDescribeEt;
    private CustomItemView mRemindCiv;
    private CustomItemView mRepeatCiv;
    private CustomItemView mDurationCiv;
    private CustomItemView mPrivacyCiv;
    private CustomItemView mRecordCiv;
    private TextView mNextTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_setting;
    }

    @Override
    protected void initView() {
        mSettingMab = findViewById(R.id.setting_mab);
        mDescribeEt = findViewById(R.id.describe_et);
        mRemindCiv = findViewById(R.id.remind_civ);
        mRepeatCiv = findViewById(R.id.repeat_civ);
        mDurationCiv = findViewById(R.id.duration_civ);
        mPrivacyCiv = findViewById(R.id.privacy_civ);
        mRecordCiv = findViewById(R.id.record_civ);
        mNextTv = findViewById(R.id.next_tv);
    }

    @Override
    protected void initListener() {
        mRemindCiv.setOnClickListener(this);
        mRepeatCiv.setOnClickListener(this);
        mDurationCiv.setOnClickListener(this);
        mPrivacyCiv.setOnClickListener(this);
        mRecordCiv.setOnClickListener(this);
        mNextTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.remind_civ:

                break;
            case R.id.repeat_civ:
                startActivity(new Intent(HabitSettingActivity.this,RepeatDayActivity.class));
                break;
            case R.id.duration_civ:
                startActivity(new Intent(HabitSettingActivity.this,TimeSettingActivity.class));
                break;
            case R.id.privacy_civ:

                break;
            case R.id.record_civ:

                break;
            case R.id.next_tv:
                startActivity(new Intent(HabitSettingActivity.this,SupervisionSettingActivity.class));
                break;
        }
    }
}
