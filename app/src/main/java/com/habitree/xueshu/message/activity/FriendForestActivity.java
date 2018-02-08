package com.habitree.xueshu.message.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.RoundImageView;

public class FriendForestActivity extends BaseActivity {

    private RoundImageView mHeadRiv;
    private TextView mNameTv;
    private TextView mHabitNameTv;
    private TextView mHabitTextTv;
    private TextView mStartTimeTv;
    private TextView mEndTimeTv;
    private SeekBar mTreeSb;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_friend_forest;
    }

    @Override
    protected void initStatusBar() {
        UIUtil.setStatusBar(this,getResources().getColor(R.color.trans));
    }

    @Override
    protected void initView() {
        mTreeSb = findViewById(R.id.tree_sb);
        mHeadRiv = findViewById(R.id.head_riv);
        mNameTv = findViewById(R.id.name_tv);
        mHabitNameTv = findViewById(R.id.habit_name_tv);
        mHabitTextTv = findViewById(R.id.habit_text_tv);
        mStartTimeTv = findViewById(R.id.start_time_tv);
        mEndTimeTv = findViewById(R.id.end_time_tv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
