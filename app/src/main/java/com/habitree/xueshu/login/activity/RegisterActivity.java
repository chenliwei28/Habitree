package com.habitree.xueshu.login.activity;


import android.content.Context;
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
    private int mType;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    public static void start(Context context,int type){
        Intent intent = new Intent(context,RegisterActivity.class);
        intent.putExtra(Constant.TYPE,type);
        context.startActivity(intent);
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
        mType = getIntent().getIntExtra(Constant.TYPE,1);
        switch (mType){
            case 1:
                mRegisterMab.setTitle(getString(R.string.register_account));
                break;
            case 2:
                mRegisterMab.setTitle(getString(R.string.forget_password));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.next_tv:
                CommUtil.hideSoftInput(this);
                checkPhoneAndToNext();
                break;
        }
    }

    private void checkPhoneAndToNext(){
        String phone = mPhoneEt.getText().toString();
        if (CommUtil.isPhoneNumber(this,phone)){
            SendAuthCodeActivity.start(this,phone,mType);
        }
    }
}
