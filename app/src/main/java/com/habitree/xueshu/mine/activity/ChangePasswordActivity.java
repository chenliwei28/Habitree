package com.habitree.xueshu.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener{

    private EditText mPasswordEt;
    private EditText mAgainEt;
    private TextView mNextTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initView() {
        mPasswordEt = findViewById(R.id.password_et);
        mAgainEt = findViewById(R.id.again_et);
        mNextTv = findViewById(R.id.next_tv);
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
            case R.id.next_tv:

                break;
        }
    }
}
