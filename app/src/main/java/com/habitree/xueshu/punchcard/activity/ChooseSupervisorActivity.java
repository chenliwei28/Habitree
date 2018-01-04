package com.habitree.xueshu.punchcard.activity;

import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;

public class ChooseSupervisorActivity extends BaseActivity implements View.OnClickListener{

    private MyActionBar mChooseMab;
    private CustomItemView mFriendsCiv;
    private CustomItemView mWxCiv;
    private CustomItemView mQqCiv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_choose_supervisor;
    }

    @Override
    protected void initView() {
        mChooseMab = findViewById(R.id.choose_mab);
        mFriendsCiv = findViewById(R.id.friends_civ);
        mWxCiv = findViewById(R.id.wx_civ);
        mQqCiv = findViewById(R.id.qq_civ);
    }

    @Override
    protected void initListener() {
        mChooseMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(ChooseSupervisorActivity.this);
            }
        });
        mFriendsCiv.setOnClickListener(this);
        mWxCiv.setOnClickListener(this);
        mQqCiv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.friends_civ:

                break;
            case R.id.wx_civ:

                break;
            case R.id.qq_civ:

                break;
        }
    }
}
