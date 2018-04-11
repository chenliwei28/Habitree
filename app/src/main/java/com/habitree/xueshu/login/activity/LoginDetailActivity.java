package com.habitree.xueshu.login.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.constant.SMSType;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.LoginView;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.main.MainActivity;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.AuthCodeTimer;

/**
 * 登录界面(密码|验证码)
 *
 * @author wuxq
 */
public class LoginDetailActivity extends BaseActivity implements RegisterView.AuthCodeView, LoginView, OnClickListener {

    private final int TYPE_PASSWORD = 1;
    private final int TYPE_AUTH_CODE = 2;
    private int type = TYPE_PASSWORD;

    // 密码|验证码|登录
    private TextView mPasswordBtn, mAuthCodeBtn, mLoginBtn;
    private TextView mCodeTypeTv;
    private TextView mForgetBtn;
    private LinearLayout llAuthCode, llPassword;
    // 电话|密码|验证码
    private EditText mPhoneEt, mPasswordEt, mAuthCodeEt;
    private LoginAndRegisterPresenter mPresenter;
    private ImageView mPwdVisibilityBtn;
    // 验证码发生
    private TextView mSendBtn;
    // 密码隐藏或者显示的标记
    private boolean isPasswordShow = false;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initStatusBar() {
        UIUtil.setStatusBar(this, getResources().getColor(R.color.blue));
    }

    @Override
    protected void initView() {
        mPasswordBtn = findViewById(R.id.password_btn);
        mAuthCodeBtn = findViewById(R.id.auth_code_btn);
        mCodeTypeTv = findViewById(R.id.code_type_tv);
        llAuthCode = findViewById(R.id.llAuthCode);
        llPassword = findViewById(R.id.llPassword);
        mForgetBtn = findViewById(R.id.forget_btn);
        mPhoneEt = findViewById(R.id.phone_et);
        mPasswordEt = findViewById(R.id.password_et);
        mLoginBtn = findViewById(R.id.login_btn);
        mPwdVisibilityBtn = findViewById(R.id.pwd_visibility_btn);
        mSendBtn = findViewById(R.id.send_tv);
        mAuthCodeEt = findViewById(R.id.code_et);
    }

    @Override
    protected void initListener() {
        mPasswordBtn.setOnClickListener(this);
        mAuthCodeBtn.setOnClickListener(this);
        mForgetBtn.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
        mPwdVisibilityBtn.setOnClickListener(this);
        mSendBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter = new LoginAndRegisterPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.password_btn:
                // 密码
                type = TYPE_PASSWORD;
                changeType(type);
                break;
            case R.id.auth_code_btn:
                // 验证码
                type = TYPE_AUTH_CODE;
                changeType(type);
                break;
            case R.id.forget_btn:
                // 忘记密码
                ForgetPwdActivity.start(this);
                break;
            case R.id.pwd_visibility_btn:
                // 切换密码是否可见
                isPasswordShow = !isPasswordShow;
                UIUtil.setEditTextShowStatus(mPasswordEt, isPasswordShow);
                mPwdVisibilityBtn.setImageResource(isPasswordShow ? R.drawable.ic_password_show : R.drawable.ic_password_hide);
                break;
            case R.id.login_btn:
                CommUtil.hideSoftInput(this);
                if (type == TYPE_PASSWORD) {
                    // 密码登录
                    showLoadingDialog();
                    mPresenter.login(mPhoneEt.getText().toString(), mPasswordEt.getText().toString(), this);
                } else {
                    // 验证码登录
                    String smsCode = mAuthCodeEt.getText().toString();
                    String phoneNumber = mPhoneEt.getText().toString();
                    if (TextUtils.isEmpty(smsCode)) {
                        showToast(getString(R.string.auth_code_empty));
                       return;
                    } else if (smsCode.length() != 4) {
                        showToast(getString(R.string.wrong_auth_code));
                        return;
                    }
                    showLoadingDialog();
                    mPresenter.loginWithAuthCode(phoneNumber,smsCode,SMSType.LOGIN_AUTHCODE, this);
                }
                break;
            case R.id.send_tv:
                mPresenter.sendAuthCode(mPhoneEt.getText().toString(), SMSType.LOGIN_AUTHCODE, this);
                break;
        }
    }

    /**
     * 切换类型
     *
     * @param type
     */
    private void changeType(int type) {
        mPasswordBtn.setTextColor(type == TYPE_PASSWORD ? getResources().getColor(R.color.white) : getResources().getColor(R.color.trans_white));
        mAuthCodeBtn.setTextColor(type == TYPE_PASSWORD ? getResources().getColor(R.color.trans_white) : getResources().getColor(R.color.white));
        mCodeTypeTv.setText(type == TYPE_PASSWORD ? R.string.password : R.string.auth_code);
        llPassword.setVisibility(type == TYPE_PASSWORD ? View.VISIBLE : View.GONE);
        llAuthCode.setVisibility(type == TYPE_PASSWORD ? View.GONE : View.VISIBLE);
        mForgetBtn.setVisibility(type == TYPE_PASSWORD ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onLoginSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.login_success));
        startActivity(new Intent(this, MainActivity.class));
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onLoginFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    public void onSendSuccess() {
        // 发送验证码
        AuthCodeTimer timer = new AuthCodeTimer(this, mSendBtn);
        timer.start();
    }

    @Override
    public void onSendFail(String reason) {
        showToast(reason);
    }
}