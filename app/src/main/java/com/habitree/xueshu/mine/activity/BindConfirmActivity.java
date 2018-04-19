package com.habitree.xueshu.mine.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.constant.SMSType;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView.CheckAuthCodeView;
import com.habitree.xueshu.login.pview.RegisterView.AuthCodeView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.UIUtil;
import com.habitree.xueshu.xs.util.UserManager;
import com.habitree.xueshu.xs.view.AuthCodeTimer;

/**
 * 换绑前确认
 *
 * @author wuxq
 */
public class BindConfirmActivity extends BaseActionBarActivity implements OnClickListener ,AuthCodeView,CheckAuthCodeView {

    private TextView mPhoneTv, mSendBtn, mCompletedBtn;
    private EditText mAuthCodeEt;
    private String phone;
    private LoginAndRegisterPresenter mSendPresenter;
    private AuthCodeTimer timer;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_bind_confirm;
    }

    @Override
    protected void initView() {
        mPhoneTv = findViewById(R.id.phone_tv);
        mAuthCodeEt = findViewById(R.id.authcode_et);
        mSendBtn = findViewById(R.id.send_tv);
        mCompletedBtn = findViewById(R.id.confirm_tv);
    }

    @Override
    protected void initListener() {
        mSendBtn.setOnClickListener(this);
        mCompletedBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle("确认");
        mSendPresenter = new LoginAndRegisterPresenter(this);
        phone = UserManager.getManager().getPhone();
//        if (TextUtils.isEmpty(phone)) {
//            showToast("电话号码不能为空");
//            AppManager.getAppManager().finishActivity(this);
//            return;
//        }

        mPhoneTv.setText("+86 " + phone);
        // 验证码倒计时
        timer = AuthCodeTimer.getInstance();
        timer.setTextView(mSendBtn);
    }

    @Override
    public void onClick(View view) {
        int vid = view.getId();
        if (vid == R.id.send_tv) {
            // 发送验证码
            mSendPresenter.sendAuthCode(phone, SMSType.BIND_BEFORE, this);
        } else if (vid == R.id.confirm_tv) {
            // 确认
            String code = mAuthCodeEt.getText().toString();
            if(CommUtil.isAuthCode(BindConfirmActivity.this,code)){
                mSendPresenter.checkVerifyCode(SMSType.BIND_BEFORE,code,phone,this);
            }
        }
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

    @Override
    public void onCheckSuccess() {
        UIUtil.startActivity(this,ChangePhoneActivity.class);
        if (timer != null){
            timer.cancel();
        }
    }

    @Override
    public void onCheckFailed(String reason) {
        showToast(reason);
    }
}
