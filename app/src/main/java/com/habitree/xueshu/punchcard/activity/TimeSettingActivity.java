package com.habitree.xueshu.punchcard.activity;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;

public class TimeSettingActivity extends BaseActivity implements OnClickListener{

    private TextView mConfirmTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_time_setting;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_tv:

                break;
        }
    }
}
