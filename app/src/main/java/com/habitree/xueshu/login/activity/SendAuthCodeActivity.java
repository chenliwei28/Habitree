package com.habitree.xueshu.login.activity;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.view.MyActionBar;

/**
 * 手机验证码
 */
public class SendAuthCodeActivity extends BaseActionBarActivity implements RegisterView.AuthCodeView,View.OnClickListener,RegisterView.CheckAuthCodeView{

    private MyActionBar mSendMab;
    private EditText mCodeEt;
    private TextView mSendTv;
    private TextView mNextTv;

    private final static int AUTH_RESET_TIME = 60;
    private int mTime = AUTH_RESET_TIME;
    private String mPhone;
    private String mCode;
    private int mType;
    private LoginAndRegisterPresenter mPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_send_auth_code;
    }

    public static void start(Context context,String phone,int type){
        Intent intent = new Intent(context,SendAuthCodeActivity.class);
        intent.putExtra(Constant.PHONE,phone);
        intent.putExtra(Constant.TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        mPhone = getIntent().getStringExtra(Constant.PHONE);
        mType = getIntent().getIntExtra(Constant.TYPE,1);
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
        setTitle(R.string.phone_auth_code);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_tv:
                mPresenter.sendAuthCode(mPhone,mType,this);
                break;
            case R.id.next_tv:
                checkCodeAndToNext();
                break;
        }
    }

    private void countDown(){
        mSendTv.setBackgroundResource(R.drawable.shape_rect_round_corner_gray_button);
        mSendTv.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mTime==0){
                    mTime = AUTH_RESET_TIME;
                    mSendTv.setClickable(true);
                    mSendTv.setText(R.string.resend);
                    mSendTv.setBackgroundResource(R.drawable.shape_rect_round_corner_orange_button);
                }else {
                    mSendTv.setClickable(false);
                    mTime--;
                    mSendTv.setText(String.format(getString(R.string.resend_time),mTime));
                    mSendTv.postDelayed(this,1000);
                }
            }
        },1000);
    }

    private void checkCodeAndToNext(){
        CommUtil.hideSoftInput(this);
        mCode = mCodeEt.getText().toString();
        if (TextUtils.isEmpty(mCode)){
            showToast(getString(R.string.auth_code_empty));
        }else if (mCode.length()!=4){
            showToast(getString(R.string.wrong_auth_code));
        }else {
            mPresenter.checkVerifyCode(1,mCode,mPhone);
        }
    }

    @Override
    public void onSendSuccess() {
        countDown();
    }

    @Override
    public void onSendFail(String reason) {
        showToast(reason);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onCheckSuccess() {
        SetPasswordActivity.start(this,mPhone,mCode,mType);
    }

    @Override
    public void onCheckFailed(String reason) {
        showToast(reason);
    }
}
