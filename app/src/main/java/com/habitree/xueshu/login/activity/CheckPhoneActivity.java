package com.habitree.xueshu.login.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;

import com.habitree.xueshu.constant.SMSType;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.AuthCodeTimer;

/**
 * 注册验证手机号
 * 1、验证手机号码
 * 2、发送短信验证码
 * @author wuxq
 */
public class CheckPhoneActivity extends BaseActionBarActivity implements OnClickListener,RegisterView.AuthCodeView {

    private EditText mPhoneEt;
    private TextView mNextBtn, mSecretBtn;
    private LoginAndRegisterPresenter mPresenter;
    private String phone = "";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_check_phone;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, CheckPhoneActivity.class);
        UIUtil.startActivity(context, intent);
    }

    @Override
    protected void initView() {
        mPhoneEt = findViewById(R.id.phone_et);
        mNextBtn = findViewById(R.id.next_btn);
        mSecretBtn = findViewById(R.id.secret_btn);
    }

    @Override
    protected void initListener() {
        mNextBtn.setOnClickListener(this);
        mSecretBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter = new LoginAndRegisterPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if (vid == R.id.next_btn) {
            // 下一步
            if(AuthCodeTimer.getInstance().isTiming() && phone.equals(mPhoneEt.getText().toString())){
                RegisterActivity.start(this,phone);
            }
            else{
                phone = mPhoneEt.getText().toString();
                showLoadingDialog();
                mPresenter.sendAuthCode(phone, SMSType.REGISTER, this);
            }
        } else if (vid == R.id.secret_btn) {
            // 隐私协议
        }
    }

    @Override
    public void onSendSuccess() {
        AuthCodeTimer.getInstance().reStart();
        hideLoadingDialog();
        RegisterActivity.start(this,mPhoneEt.getText().toString());
    }

    @Override
    public void onSendFail(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }
}
