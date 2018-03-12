package com.habitree.xueshu.mine.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.habitree.xueshu.R;
import com.habitree.xueshu.login.presenter.LoginAndRegisterPresenter;
import com.habitree.xueshu.login.pview.RegisterView;
import com.habitree.xueshu.xs.activity.BaseActivity;
import com.habitree.xueshu.xs.util.AppManager;
import com.habitree.xueshu.xs.util.CommUtil;
import com.habitree.xueshu.xs.util.ToastUtil;

public class ChangePhoneActivity extends BaseActivity implements View.OnClickListener, RegisterView.AuthCodeView,RegisterView.ChangeBindView {

    private EditText mNumEt;
    private TextView mSendTv;
    private EditText mCodeEt;
    private TextView mCompleteTv;
    private LoginAndRegisterPresenter mPresenter;
    private final static int AUTH_RESET_TIME = 60;
    private int mTime = AUTH_RESET_TIME;
    private String mPhone;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void initView() {
        mNumEt = findViewById(R.id.num_et);
        mSendTv = findViewById(R.id.send_tv);
        mCodeEt = findViewById(R.id.code_et);
        mCompleteTv = findViewById(R.id.completed_tv);
        mPresenter = new LoginAndRegisterPresenter(this);
    }

    @Override
    protected void initListener() {
        mSendTv.setOnClickListener(this);
        mCompleteTv.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_tv:
                mPhone = mNumEt.getText().toString();
                if (CommUtil.isPhoneNumber(this, mPhone))
                    mPresenter.sendAuthCode(mPhone, 3, this);
                break;
            case R.id.completed_tv:
                checkAndToNext();
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
        countDown();
    }

    @Override
    public void onSendFail(String reason) {
        ToastUtil.showToast(this,reason);
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
                    mSendTv.setBackgroundResource(R.drawable.shape_rect_round_corner_blue_button);
                }else {
                    mSendTv.setClickable(false);
                    mTime--;
                    mSendTv.setText(String.format(getString(R.string.resend_time),mTime));
                    mSendTv.postDelayed(this,1000);
                }
            }
        },1000);
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
