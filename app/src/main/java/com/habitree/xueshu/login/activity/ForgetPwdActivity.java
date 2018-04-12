package com.habitree.xueshu.login.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.constant.SMSType;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.login.pview.RegisterView.AuthCodeView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.AuthCodeTimer;
import com.hyphenate.easeui.model.EasePreferenceManager;

/**
 * 忘记密码界面
 *
 * @author wuxq
 */
public class ForgetPwdActivity extends BaseActivity implements OnClickListener,AuthCodeView ,RegisterView{

    private EditText mPasswordEt, mAuthCodeEt,mPhoneEt;
    // 验证码发生
    private TextView mSendBtn, mCompleteBtn;
    private ImageView mPwdVisibilityBtn;
    private LoginAndRegisterPresenter mPresenter;
    // 密码隐藏或者显示的标记
    private boolean isPasswordShow = false;
    private AuthCodeTimer timer;
    private String phone ="";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ForgetPwdActivity.class);
        UIUtil.startActivity(context, intent);
    }

    @Override
    protected void initView() {
        mPasswordEt = findViewById(R.id.password_et);
        mAuthCodeEt = findViewById(R.id.code_et);
        mSendBtn = findViewById(R.id.send_tv);
        mPwdVisibilityBtn = findViewById(R.id.pwd_visibility_btn);
        mCompleteBtn = findViewById(R.id.complete_btn);
        mPhoneEt = findViewById(R.id.phone_et);
    }

    @Override
    protected void initListener() {
        mCompleteBtn.setOnClickListener(this);
        mSendBtn.setOnClickListener(this);
        mPwdVisibilityBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter = new LoginAndRegisterPresenter(this);
        // 验证码倒计时
        timer = AuthCodeTimer.getInstance();
        timer.setTextView(mSendBtn);
        phone = EasePreferenceManager.getInstance().getStringValue("et_phone","");
        mPhoneEt.setText(phone);
        if(!TextUtils.isEmpty(phone)){
            mPhoneEt.setText(phone);
            mPhoneEt.setSelection(phone.length());
        }
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        phone = mPhoneEt.getText().toString();
        CommUtil.hideSoftInput(this);
        switch (vid){
            case R.id.send_tv:
                EasePreferenceManager.getInstance().setStringValue("et_phone",phone);
                // 发送验证码
                if (!CommUtil.isPhoneNumber(this, phone)) {
                    break;
                }
                showLoadingDialog();
                mPresenter.sendAuthCode(phone, SMSType.FORGET, this);
                break;
            case R.id.pwd_visibility_btn:
                // 切换密码是否可见
                isPasswordShow = !isPasswordShow;
                UIUtil.setEditTextShowStatus(mPasswordEt, isPasswordShow);
                mPwdVisibilityBtn.setImageResource(isPasswordShow ? R.drawable.ic_password_show : R.drawable.ic_password_hide);
                break;
            case R.id.complete_btn:
                //完成
                String authCode = mAuthCodeEt.getText().toString();
                String password = mPasswordEt.getText().toString();
                if (!CommUtil.isPhoneNumber(this, phone)) {
                    break;
                }
                if (!CommUtil.isAuthCode(this, authCode)) {
                    break;
                }
                if (!CommUtil.isPassword(this, password)) {
                    break;
                }
                showLoadingDialog();
                mPresenter.findPassword(phone,password,authCode,this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onSendSuccess() {
        hideLoadingDialog();
        // 发送验证码
        timer.reStart();
    }

    @Override
    public void onSendFail(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    @Override
    public void onRegisterSuccess() {
        if (timer != null){
            timer.cancel();
        }
        hideLoadingDialog();
        showToast(getString(R.string.find_password_success));
        AppManager.getAppManager().finishActivity(this);
        finish();
    }

    @Override
    public void onRegisterFail(String reason) {
        showToast(reason);
        hideLoadingDialog();
    }
}
