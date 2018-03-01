package com.habitree.xueshu.login.activity;



import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;

public class SetPasswordActivity extends BaseActivity implements RegisterView {

    private EditText mPasswordEt;
    private EditText mAgainEt;
    private TextView mNextTv;
    private String mPhone;
    private String mCode;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_set_password;
    }

    public static void start(Context context,String phone,String code){
        Intent intent = new Intent(context,SetPasswordActivity.class);
        intent.putExtra(Constant.PHONE,phone);
        intent.putExtra(Constant.CODE,code);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mPhone = getIntent().getStringExtra(Constant.PHONE);
        mCode = getIntent().getStringExtra(Constant.CODE);
        mPasswordEt = findViewById(R.id.password_et);
        mAgainEt = findViewById(R.id.again_et);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
}
