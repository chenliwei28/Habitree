package com.habitree.xueshu.mine.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.activity.BaseActionBarActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.ToastUtil;
import com.habitree.xueshu.xs.view.AuthCodeTimer;

/**
 * 更换手机
 */
public class ChangePhoneActivity extends BaseActionBarActivity implements View.OnClickListener, RegisterView.AuthCodeView,RegisterView.ChangeBindView {

    // 验证码发生
    private TextView mSendBtn, mCompleteBtn;

    private EditText mNumEt;
    private EditText mCodeEt;
    private LoginAndRegisterPresenter mPresenter;
    private String mPhone;
    private AuthCodeTimer timer;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void initView() {
        mSendBtn = findViewById(R.id.send_tv);
        mCompleteBtn = findViewById(R.id.complete_btn);

        mNumEt = findViewById(R.id.phone_et);
        mCodeEt = findViewById(R.id.code_et);
    }

    @Override
    protected void initListener() {
        mCompleteBtn.setOnClickListener(this);
        mSendBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        setTitle(R.string.change_phone_number);
        mPresenter = new LoginAndRegisterPresenter(this);
        // 验证码倒计时
        timer = AuthCodeTimer.getInstance();
        timer.setTextView(mSendBtn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_tv:
                mPhone = mNumEt.getText().toString();
                if (CommUtil.isPhoneNumber(this, mPhone))
                    mPresenter.sendAuthCode(mPhone, 3, this);
                break;
            case R.id.complete_btn:
                checkAndToNext();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDetach();
        if(timer != null){
            timer.cancel();
        }
    }

    @Override
    public void onSendSuccess() {
        hideLoadingDialog();
        // 发送验证码
        timer.start();
    }

    @Override
    public void onSendFail(String reason) {
        ToastUtil.showToast(this,reason);
    }

    private void checkAndToNext(){
        String phone = mNumEt.getText().toString();
        if (!CommUtil.isPhoneNumber(this,phone))return;
        if (mCodeEt.getText().length()!=4){
            showToast(getString(R.string.wrong_auth_code));
            return;
        }
        if (!mPhone.equals(phone)) {
            showToast(getString(R.string.auth_code_not_fit_phone));
            return;
        }
        mPresenter.changeBindPhone(mPhone,mCodeEt.getText().toString(),this);
    }

    @Override
    public void onChangeSuccess() {
        showToast(getString(R.string.change_success));
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onChangeFail(String reason) {
        showToast(reason);
    }
}
