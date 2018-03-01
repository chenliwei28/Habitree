package com.habitree.xueshu.login.activity;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.view.MyActionBar;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private MyActionBar mRegisterMab;
    private EditText mPhoneEt;
    private TextView mNextTv;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        mRegisterMab = findViewById(R.id.register_mab);
        mPhoneEt = findViewById(R.id.phone_et);
        mNextTv = findViewById(R.id.next_tv);
    }

    @Override
    protected void initListener() {
        mNextTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        String title = getIntent().getStringExtra(Constant.TITLE);
        if (title!=null)mRegisterMab.setTitle(title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_tv:
                checkPhoneAndToNext();
                break;
        }
    }

    private void checkPhoneAndToNext(){
        String phone = mPhoneEt.getText().toString();
        if (CommUtil.isPhoneNumber(this,phone)){
            SendAuthCodeActivity.start(this,phone);
        }
    }
}
