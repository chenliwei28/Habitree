package com.habitree.xueshu.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.HabitStateAdapter;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.MyActionBar;

public class HabitOngoingOrNotActivity extends BaseActivity {

    private TextView mCountTv;
    private ListView mHabitsLv;

    private boolean mCompleted;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_habit_ongoing_or_not;
    }

    @Override
    protected void initView() {
        mCompleted = getIntent().getBooleanExtra(Constant.TYPE,false);
        MyActionBar mActionBar = findViewById(R.id.action_bar);
        TextView mStateTv = findViewById(R.id.state_tv);
        mCountTv = findViewById(R.id.count_tv);
        mHabitsLv = findViewById(R.id.habits_lv);
        mActionBar.setTitle(mCompleted?getString(R.string.completed):getString(R.string.ongoing));
        mStateTv.setText(mCompleted?getString(R.string.now_completed):getString(R.string.now_ongoing));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        HabitStateAdapter adapter = new HabitStateAdapter(this,mCompleted);
        mHabitsLv.setAdapter(adapter);
    }

    public static void start(Context context,boolean completed){
        Intent intent = new Intent(context,HabitOngoingOrNotActivity.class);
        intent.putExtra(Constant.TYPE,completed);
        context.startActivity(intent);
    }
}
