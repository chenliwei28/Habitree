package com.habitree.xueshu.punchcard.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.CustomItemView;
import com.habitree.xueshu.xs.view.MyActionBar;

public class SupervisionSettingActivity extends BaseActivity implements View.OnClickListener{

    private CustomItemView mSuperCiv;
    private CustomItemView mPenaltyCiv;
    private TextView mConfirmTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_supervision_setting;
    }

    @Override
    protected void initView() {
        mSuperCiv = findViewById(R.id.super_civ);
        mPenaltyCiv = findViewById(R.id.penalty_civ);
        mConfirmTv = findViewById(R.id.confirm_tv);
    }

    @Override
    protected void initListener() {
        mSuperCiv.setOnClickListener(this);
        mPenaltyCiv.setOnClickListener(this);
        mConfirmTv.setOnClickListener(this);
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
                startActivity(new Intent(SupervisionSettingActivity.this,ForfeitSettingActivity.class));

                break;
            case R.id.confirm_tv:

                break;
        }
    }
}
