package com.habitree.xueshu.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.xs.activity.BaseActivity;

public class ChangePhoneActivity extends BaseActivity implements View.OnClickListener{

    private EditText mNumEt;
    private TextView mSendTv;
    private EditText mCodeEt;
    private TextView mCompleteTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void initView() {
        mNumEt = findViewById(R.id.num_et);
        mSendTv = findViewById(R.id.send_tv);
        mCodeEt = findViewById(R.id.code_et);
        mCompleteTv = findViewById(R.id.completed_tv);
    }

    @Override
    protected void initListener() {
        mSendTv.setOnClickListener(this);
        mCompleteTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_tv:

                break;
            case R.id.completed_tv:

                break;
        }
    }
}
