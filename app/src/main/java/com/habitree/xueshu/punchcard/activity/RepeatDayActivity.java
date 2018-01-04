package com.habitree.xueshu.punchcard.activity;

import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;

public class RepeatDayActivity extends BaseActivity implements View.OnClickListener{

    private MyActionBar mDayMab;
    private CustomItemView mMondayCiv;
    private CustomItemView mTuesdayCiv;
    private CustomItemView mWednesdayCiv;
    private CustomItemView mThursdayCiv;
    private CustomItemView mFridayCiv;
    private CustomItemView mSaturdayCiv;
    private CustomItemView mSundayCiv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_repeat_day;
    }

    @Override
    protected void initView() {
        mDayMab = findViewById(R.id.day_mab);
        mMondayCiv = findViewById(R.id.monday_civ);
        mTuesdayCiv = findViewById(R.id.tuesday_civ);
        mWednesdayCiv = findViewById(R.id.wednesday_civ);
        mThursdayCiv = findViewById(R.id.thursday_civ);
        mFridayCiv = findViewById(R.id.friday_civ);
        mSaturdayCiv = findViewById(R.id.saturday_civ);
        mSundayCiv = findViewById(R.id.sunday_civ);
    }

    @Override
    protected void initListener() {
        mMondayCiv.setOnClickListener(this);
        mTuesdayCiv.setOnClickListener(this);
        mWednesdayCiv.setOnClickListener(this);
        mThursdayCiv.setOnClickListener(this);
        mFridayCiv.setOnClickListener(this);
        mSaturdayCiv.setOnClickListener(this);
        mSundayCiv.setOnClickListener(this);
        mDayMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(RepeatDayActivity.this);
            }
        });
        mDayMab.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.monday_civ:
                mMondayCiv.setChecked(!mMondayCiv.mIsSelected);
                break;
            case R.id.tuesday_civ:
                mTuesdayCiv.setChecked(!mTuesdayCiv.mIsSelected);
                break;
            case R.id.wednesday_civ:
                mWednesdayCiv.setChecked(!mWednesdayCiv.mIsSelected);
                break;
            case R.id.thursday_civ:
                mThursdayCiv.setChecked(!mThursdayCiv.mIsSelected);
                break;
            case R.id.friday_civ:
                mFridayCiv.setChecked(!mFridayCiv.mIsSelected);
                break;
            case R.id.saturday_civ:
                mSaturdayCiv.setChecked(!mSaturdayCiv.mIsSelected);
                break;
            case R.id.sunday_civ:
                mSundayCiv.setChecked(!mSundayCiv.mIsSelected);
                break;
        }
    }
}
