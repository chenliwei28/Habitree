package com.habitree.xueshu.mine.activity;


import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.constant.SMSType;
import com.habitree.xueshu.login.activity.LoginActivity;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView.AuthCodeView;
import com.habitree.xueshu.mine.presenter.MyPresenter;
import com.habitree.xueshu.mine.pview.MyView.ChangePaswView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.AuthCodeTimer;

/**
 * 修改密码
 */
public class ChangePasswordActivity extends BaseActionBarActivity implements OnClickListener, ChangePaswView, AuthCodeView {

    private EditText mPasswordEt, mAuthCodeEt;
    private TextView mNextTv, mSendBtn, mPhoneTv;
    private MyPresenter mPresenter;
    private LoginAndRegisterPresenter mSendPresenter;
    private AuthCodeTimer timer;
    private String phone;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    protected void initView() {
        mPhoneTv = findViewById(R.id.phone_tv);
        mPasswordEt = findViewById(R.id.new_pwd_et);
        mAuthCodeEt = findViewById(R.id.authcode_et);
        mSendBtn = findViewById(R.id.send_tv);
        mNextTv = findViewById(R.id.confirm_tv);
    }

    @Override
    protected void initListener() {
        mNextTv.setOnClickListener(this);
        mSendBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.change_password);
        phone = UserManager.getManager().getUser().mobile;
        if (TextUtils.isEmpty(phone)) {
            showToast("电话号码不能为空");
            AppManager.getAppManager().finishActivity(this);
            return;
        }
        mPhoneTv.setText("+86 " + phone);
        mPresenter = new MyPresenter(this);
        mSendPresenter = new LoginAndRegisterPresenter(this);
        // 验证码倒计时
        timer = AuthCodeTimer.getInstance();
        timer.setTextView(mSendBtn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_tv:
                checkAndConfirm();
                break;
            case R.id.send_tv:
                // 发送验证码
                if (CommUtil.isPhoneNumber(this, phone)) {
                    mSendPresenter.sendAuthCode(phone, SMSType.CHANGE_PWD, this);
                }
                break;
        }
    }

    private void checkAndConfirm() {
        CommUtil.hideSoftInput(this);
        String pwd = mPasswordEt.getText().toString();
        String authCode = mAuthCodeEt.getText().toString();
        if (!CommUtil.isPhoneNumber(this, phone)) return;

        if (!CommUtil.isAuthCode(this, authCode)) return;
        if (!CommUtil.isPassword(this, pwd)) return;
        showLoadingDialog();
        mPresenter.changePassword(phone, pwd, authCode, this);
    }

    @Override
    public void onChangePsSuccess() {
        hideLoadingDialog();
        showToast(getString(R.string.change_success));
        UIUtil.startActivity(this, LoginActivity.class);
        AppManager.getAppManager().finishActivity(this);

    }

    @Override
    public void onChangePsFailed(String reason) {
        hideLoadingDialog();
        showToast(reason);
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
        showToast(reason);
    }
}
