package com.habitree.xueshu.punchcard.activity;

import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;

public class ChooseSupervisorActivity extends BaseActivity {

    private MyActionBar mChooseMab;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_choose_supervisor;
    }

    @Override
    protected void initView() {
        mChooseMab = findViewById(R.id.choose_mab);
    }

    @Override
    protected void initListener() {
        mChooseMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(ChooseSupervisorActivity.this);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
