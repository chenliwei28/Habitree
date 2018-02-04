package com.habitree.xueshu.punchcard.activity;

import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.punchcard.presenter.HabitPresenter;
import com.habitree.xueshu.punchcard.pview.HabitView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;
import com.habitree.xueshu.xs.view.RoundImageView;
import com.habitree.xueshu.xs.view.calendarview.Calendar;
import com.habitree.xueshu.xs.view.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

public class HabitDetailActivity extends BaseActivity implements HabitView,View.OnClickListener {

    private MyActionBar mDetailMab;
    private CalendarView mDetailCv;
    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mTimeTv;
    private TextView mCountTv;
    private CustomItemView mReminderCiv;
    private CustomItemView mRepeatCiv;
    private CustomItemView mDurationCiv;
    private CustomItemView mPrivacyCiv;
    private CustomItemView mModCiv;
    private CustomItemView mPenaltyCiv;
    private CustomItemView mPaidCiv;
    private CustomItemView mFosterCiv;
    private CustomItemView mTotalCiv;
    private CustomItemView mContinuousCiv;
    private CustomItemView mRateCiv;
    private CustomItemView mNeedCiv;
    private TextView mAbandonTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_detail;
    }

    @Override
    protected void initView() {
        mDetailMab = findViewById(R.id.habit_detail_mab);
        mDetailCv = findViewById(R.id.detail_cv);
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mTimeTv = findViewById(R.id.time_tv);
        mCountTv = findViewById(R.id.count_tv);
        mReminderCiv = findViewById(R.id.reminder_civ);
        mRepeatCiv = findViewById(R.id.repeat_civ);
        mDurationCiv = findViewById(R.id.duration_civ);
        mPrivacyCiv = findViewById(R.id.privacy_civ);
        mModCiv = findViewById(R.id.mod_civ);
        mPenaltyCiv = findViewById(R.id.penalty_civ);
        mPaidCiv = findViewById(R.id.paid_civ);
        mFosterCiv = findViewById(R.id.foster_civ);
        mTotalCiv = findViewById(R.id.total_civ);
        mContinuousCiv = findViewById(R.id.continuous_civ);
        mRateCiv = findViewById(R.id.rate_civ);
        mNeedCiv = findViewById(R.id.need_civ);
        mAbandonTv = findViewById(R.id.abandon_tv);
        mDetailCv.setCanSelected(false);
    }

    @Override
    protected void initListener() {
        mAbandonTv.setOnClickListener(this);
        mDetailMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppManager.getAppManager().finishActivity(HabitDetailActivity.this);
            }
        });
    }

    @Override
    protected void initData() {
        List<Calendar> calendars = new ArrayList<>();
        Calendar c = new Calendar();
        c.setYear(2017);
        c.setMonth(12);
        c.setDay(31);
        c.setScheme(".");
        calendars.add(c);
        mDetailCv.setSchemeDate(calendars);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.abandon_tv:

                break;
        }
    }
}
