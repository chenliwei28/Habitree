package com.habitree.xueshu.message.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.RoundImageView;

public class HabitInviteActivity extends BaseActivity implements View.OnClickListener{

    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mRestTimeTv;
    private TextView mDetailTv;
    private CustomItemView mHabitNameCiv;
    private CustomItemView mRepeatCiv;
    private CustomItemView mDurationCiv;
    private CustomItemView mModCiv;
    private CustomItemView mSettingCiv;
    private CustomItemView mMoneyCiv;
    private TextView mRefuseTv;
    private TextView mAcceptTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_invite;
    }

    @Override
    protected void initView() {
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mTimeTv = findViewById(R.id.time_tv);
        mRestTimeTv = findViewById(R.id.rest_time_tv);
        mDetailTv = findViewById(R.id.detail_tv);
        mHabitNameCiv = findViewById(R.id.habit_name_civ);
        mRepeatCiv = findViewById(R.id.repeat_civ);
        mDurationCiv = findViewById(R.id.duration_civ);
        mModCiv = findViewById(R.id.mod_civ);
        mSettingCiv = findViewById(R.id.setting_civ);
        mMoneyCiv = findViewById(R.id.money_civ);
        mRefuseTv = findViewById(R.id.refuse_tv);
        mAcceptTv = findViewById(R.id.accept_tv);
    }

    @Override
    protected void initListener() {
        mRefuseTv.setOnClickListener(this);
        mAcceptTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.refuse_tv:

                break;
            case R.id.accept_tv:

                break;
        }
    }
}
