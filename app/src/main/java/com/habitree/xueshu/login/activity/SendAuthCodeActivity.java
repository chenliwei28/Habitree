package com.habitree.xueshu.login.activity;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.view.MyActionBar;

public class SendAuthCodeActivity extends BaseActivity implements RegisterView.AuthCodeView,View.OnClickListener{

    private MyActionBar mSendMab;
    private EditText mCodeEt;
    private TextView mSendTv;
    private TextView mNextTv;

    private final static int AUTH_RESET_TIME = 60;
    private int mTime = AUTH_RESET_TIME;
    private String mPhone;
    private LoginAndRegisterPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_send_auth_code;
    }

    public static void start(Context context,String phone){
        Intent intent = new Intent(context,SendAuthCodeActivity.class);
        intent.putExtra(Constant.PHONE,phone);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mPhone = getIntent().getStringExtra(Constant.PHONE);
        mPresenter = new LoginAndRegisterPresenter(this);
        mSendMab = findViewById(R.id.send_mab);
        mCodeEt = findViewById(R.id.code_et);
        mSendTv = findViewById(R.id.send_tv);
        mNextTv = findViewById(R.id.next_tv);
    }

    @Override
    protected void initListener() {
        mSendTv.setOnClickListener(this);
        mNextTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_tv:
                mPresenter.sendAuthCode(mPhone,1,this);
                break;
            case R.id.next_tv:
                startActivity(new Intent(this,SetPasswordActivity.class));
                break;
        }
    }

    private void countDown(){
        mSendTv.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mTime==0){
                    mTime = AUTH_RESET_TIME;
                    mSendTv.setClickable(true);
                    mSendTv.setText(R.string.resend);
                }else {
                    mSendTv.setClickable(false);
                    mTime--;
                    mSendTv.setText(String.format(getString(R.string.resend_time),mTime));
                    mSendTv.postDelayed(this,1000);
                }
            }
        },1000);
    }

    @Override
    public void onSendSuccess() {
        countDown();
    }

    @Override
    public void onSendFail() {

    }
}
