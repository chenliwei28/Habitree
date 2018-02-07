package com.habitree.xueshu.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.habitree.xueshu.R;
import com.habitree.xueshu.mine.adapter.MyHabitsAdapter;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.NoScrollListView;

public class MyHabitsActivity extends BaseActivity {

    private NoScrollListView mOngoingLv;
    private NoScrollListView mCompletedLv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_my_habits;
    }

    @Override
    protected void initView() {
        mOngoingLv = findViewById(R.id.ongoing_lv);
        mCompletedLv = findViewById(R.id.completed_lv);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        MyHabitsAdapter adapter = new MyHabitsAdapter(this);
        mOngoingLv.setAdapter(adapter);
        mCompletedLv.setAdapter(adapter);
    }
}
