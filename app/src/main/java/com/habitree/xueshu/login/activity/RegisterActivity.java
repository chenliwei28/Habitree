package com.habitree.xueshu.login.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.constant.SMSType;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.login.pview.RegisterView.AuthCodeView;
import com.habitree.xueshu.main.MainActivity;
import com.habitree.xueshu.xs.Constant;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.view.AuthCodeTimer;

import java.lang.ref.WeakReference;

/**
 * 用户注册界面
 *
 * @author wuxq
 */
public class RegisterActivity extends BaseActionBarActivity implements View.OnClickListener, AuthCodeView, RegisterView {

    private TextView mSendTipTv;
    private EditText mPasswordEt, mAuthCodeEt;
    // 验证码发生
    private TextView mSendBtn, mNextBtn;
    private ImageView mPwdVisibilityBtn;
    private String phoneNumber;
    private LoginAndRegisterPresenter mPresenter;
    // 密码隐藏或者显示的标记
    private boolean isPasswordShow = false;
    private AuthCodeTimer timer;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    public static void start(Context context, String phone) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(Constant.PHONE, phone);
        UIUtil.startActivity(context, intent);
    }

    @Override
    protected void initView() {
        mSendTipTv = findViewById(R.id.sended_tip_btn);
        mPasswordEt = findViewById(R.id.password_et);
        mAuthCodeEt = findViewById(R.id.code_et);
        mSendBtn = findViewById(R.id.send_tv);
        mPwdVisibilityBtn = findViewById(R.id.pwd_visibility_btn);
        mNextBtn = findViewById(R.id.next_btn);
    }

    @Override
    protected void initListener() {
        mSendBtn.setOnClickListener(this);
        mPwdVisibilityBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        phoneNumber = getIntent().getStringExtra(Constant.PHONE);
        mPresenter = new LoginAndRegisterPresenter(this);
        // 验证码倒计时
        timer = AuthCodeTimer.getInstance();
        timer.setTextView(mSendBtn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_tv:
                // 发送验证码
                showLoadingDialog();
                CommUtil.hideSoftInput(this);
                mPresenter.sendAuthCode(phoneNumber, SMSType.REGISTER, this);
                break;
            case R.id.next_btn:
                // 注册
                String authCode = mAuthCodeEt.getText().toString();
                String password = mPasswordEt.getText().toString();
                if (!CommUtil.isAuthCode(this, authCode)) {
                    break;
                }
                if (!CommUtil.isPassword(this, password)) {
                    break;
                }
                showLoadingDialog();
                mPresenter.register(phoneNumber, password, authCode, this);
                break;
            case R.id.pwd_visibility_btn:
                // 切换密码是否可见
                isPasswordShow = !isPasswordShow;
                UIUtil.setEditTextShowStatus(mPasswordEt, isPasswordShow);
                mPwdVisibilityBtn.setImageResource(isPasswordShow ? R.drawable.ic_password_show : R.drawable.ic_password_hide);
                break;
        }
    }

    @Override
    public void onSendSuccess() {
        hideLoadingDialog();
        // 发送验证码
        timer.reStart();
        timer.start();

        String tip = getResources().getString(R.string.auth_code_send_to);
        tip = tip.replace("#", phoneNumber);
        mSendTipTv.setText(tip);
        mSendTipTv.setVisibility(View.VISIBLE);
        handler.sendEmptyMessageDelayed(0x001, 3 * 1000);
    }

    @Override
    public void onSendFail(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    private Handler handler = new MyHandler(this);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
    }

    @Override
    public void onRegisterSuccess() {
        // 注册成功
        if(timer != null){
            timer.cancel();
        }
        hideLoadingDialog();
        showToast(getString(R.string.register_success));
        startActivity(new Intent(this, MainActivity.class));
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onRegisterFail(String reason) {
        hideLoadingDialog();
        showToast(reason);
    }

    private static final class MyHandler extends Handler {
        private final WeakReference<RegisterActivity> mActivity;

        public MyHandler(RegisterActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RegisterActivity activity;
            if (mActivity == null || (activity = mActivity.get()) == null)
                return;
            switch (msg.what) {
                case 0x0001:
                    activity.mSendTipTv.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }
}
