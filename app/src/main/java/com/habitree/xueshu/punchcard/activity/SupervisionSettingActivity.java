package com.habitree.xueshu.punchcard.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;

public class SupervisionSettingActivity extends BaseActivity implements View.OnClickListener{

    private MyActionBar mSuperMab;
    private CustomItemView mSuperCiv;
    private CustomItemView mPenaltyCiv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_supervision_setting;
    }

    @Override
    protected void initView() {
        mSuperMab = findViewById(R.id.super_mab);
        mSuperCiv = findViewById(R.id.super_civ);
        mPenaltyCiv = findViewById(R.id.penalty_civ);
    }

    @Override
    protected void initListener() {
        mSuperMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(SupervisionSettingActivity.this);
            }
        });
        mSuperMab.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSuperCiv.setOnClickListener(this);
        mPenaltyCiv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.super_civ:
                startActivity(new Intent(SupervisionSettingActivity.this,ChooseSupervisorActivity.class));
                break;
            case R.id.penalty_civ:

                break;
        }
    }
}
