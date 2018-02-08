package com.habitree.xueshu.message.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.message.adapter.HabitFriendDetailAdapter;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.MyActionBar;
import com.habitree.xueshu.xs.view.RoundImageView;

public class FriendDetailsActivity extends BaseActivity {

    private MyActionBar mActionBar;
    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mDaysTv;
    private TextView mDianZanTv;
    private TextView mCountTv;
    private TextView mRateTv;
    private TextView mCompletedTv;
    private TextView mOngoingTv;
    private TextView mBecomeDaysTv;
    private TextView mBecomeTimesTv;
    private TextView mBecomeCountTv;
    private ListView mHabitsLv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_friend_details;
    }

    @Override
    protected void initView() {
        mActionBar = findViewById(R.id.action_bar);
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mDaysTv = findViewById(R.id.days_tv);
        mCountTv = findViewById(R.id.count_tv);
        mRateTv = findViewById(R.id.rate_tv);
        mCompletedTv = findViewById(R.id.completed_tv);
        mOngoingTv = findViewById(R.id.ongoing_tv);
        mDianZanTv = findViewById(R.id.dian_zan_tv);
        mBecomeDaysTv = findViewById(R.id.become_days_tv);
        mBecomeTimesTv = findViewById(R.id.become_times_tv);
        mBecomeCountTv = findViewById(R.id.become_count_tv);
        mHabitsLv = findViewById(R.id.habits_lv);
    }

    @Override
    protected void initListener() {
        mActionBar.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FriendDetailsActivity.this,FriendForestActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        HabitFriendDetailAdapter adapter = new HabitFriendDetailAdapter(this);
        mHabitsLv.setAdapter(adapter);
    }
}
