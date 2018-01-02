package com.habitree.xueshu.login.activity;


import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.RegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.activity.BasePresenterActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.view.MyActionBar;

public class SendAuthCodeActivity extends BasePresenterActivity<RegisterPresenter> implements RegisterView,View.OnClickListener{

    private MyActionBar mSendMab;
    private EditText mCodeEt;
    private TextView mSendTv;
    private TextView mHintTv;

    private final static int AUTH_RESET_TIME = 60;
    private int mTime = AUTH_RESET_TIME;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_send_auth_code;
    }

    @Override
    protected void initView() {
        mSendMab = findViewById(R.id.send_mab);
        mCodeEt = findViewById(R.id.code_et);
        mSendTv = findViewById(R.id.send_tv);
        mHintTv = findViewById(R.id.hint_tv);
    }

    @Override
    protected void initListener() {
        mSendMab.setBackIvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(SendAuthCodeActivity.this);
            }
        });
        mSendMab.setRightTvClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mSendTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_tv:
                countDown();
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
}
